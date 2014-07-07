package Demo;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.android.preview.support.wearable.notifications.ApplicationController;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by ljxi_828 on 6/17/14.
 */
public class NetworkManager {
    public static String mResponse;

    /* Use Volley to fetch XML content from the server */
    public static void fetchXML(String url) {

        StringRequest req = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                VolleyLog.v("Response:%n %s", response);
                mResponse = response;

                try {
                    // Transformed to object model
                    DepartureModel departureModel = MyXMLParser.parseXML(response);
                } catch (XmlPullParserException e) {
                    Log.e("fetchXML", e.toString());
                } catch (IOException e) {
                    Log.e("fetchXML", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        // Add the request object to the queue to be executed
        ApplicationController.getInstance().addToRequestQueue(req);
    }
}
