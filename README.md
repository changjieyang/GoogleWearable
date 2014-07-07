A simple demo project that grabs realtime BART departure schedule, and show on wearable emulator
 using Google Wearable Preview SDK.

Utilized below Open Source project:

Android Volley - Async StringRequest to fetch XML data 
http://www.androidhive.info/2014/05/android-working-with-volley-library-1/

Used XmlPullParser to parse XML data to object model.  

More info on the Google Wearable SDK:  
http://developer.android.com/wear/index.html

Installation:
1) Download the wearable emulator using SDK manager
2) Start the emulator
3) On your Android Phone, launch Android Wearable Preview App
4) Open terminal, run this command "adb  -d forward tcp:5601 tcp:5601"
5) Run/Debug project on your Android Phone.