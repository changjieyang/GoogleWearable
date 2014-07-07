package Demo;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by ljxi_828 on 6/14/14.
 */
public class MyXMLParser {

    /**
     * In this example we're only interested in departure time from a particular stop to "SF Airport",
     * so we'll skip other XML data.
     * <p/>
     * Example:
     * http://wptrafficanalyzer.in/blog/android-xml-parsing-with-xmlpullparser-and-loading-to-listview-example/
     * Developer API:
     * http://developer.android.com/reference/org/xmlpull/v1/XmlPullParser.html
     */
    public static DepartureModel parseXML(String xml) throws XmlPullParserException, IOException {
        XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
        XmlPullParser myParser = xmlFactoryObject.newPullParser();
        //myParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);

        /* Set string xml as input, and find the "Route" tag */
        myParser.setInput(new StringReader(xml));
        myParser.nextTag();
        myParser.nextTag();
        myParser.nextTag();
        myParser.nextTag();
        myParser.nextTag();

        return getRouteInfo(myParser);
    }

    /* Get the specific route */
    private static DepartureModel getRouteInfo(XmlPullParser parser) throws IOException, XmlPullParserException {
        DepartureModel departureModel = new DepartureModel();

        // Assert if the current tag is what we're looking for
        parser.require(XmlPullParser.START_TAG, null, "Route");

        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() != XmlPullParser.START_TAG)
                continue;

            // Get current tag name
            String name = parser.getName();

            if (name.equals("Route")) {
                String routeName = parser.getAttributeValue(null, "Name");
                if (routeName.equals("SF Airport")) {
                    // Find departure time
                    departureModel.setRouteName(routeName);
                    while (parser.next() != XmlPullParser.END_DOCUMENT) {
                        if (parser.getEventType() != XmlPullParser.START_TAG)
                            continue;

                        name = parser.getName();
                        if (name.equals("DepartureTime")) {
                            departureModel.setMinutes(Integer.parseInt(readText(parser)));
                            return departureModel;

                        } else if (name.equals("Stop")) {
                            departureModel.setLineName(parser.getAttributeValue(null, "name"));
                        }
                    }
                }

            } else {
                // Skip this tag, and its sub tags
                skip(parser);
            }
        }
        return departureModel;
    }

    /**
     * Getting Text from an element
     */
    private static String readText(XmlPullParser parser)
            throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private static void skip(XmlPullParser parser)
            throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
                case XmlPullParser.END_DOCUMENT:
                    break;
            }
        }
    }
}
