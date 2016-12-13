package com.example.ti.hotspotdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {

    CustomAdapter customAdapter;

    ArrayList<PlaceDetails>venuesList;
    ListView lv;
    TextView text1, text2,text3,text4;
    final String GOOGLE_KEY = "AIzaSyBrsXdDQ12tsgBGsbn-KYyuluIs2Ho-zaQ";

    String Place_id;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        new googleplaces().execute();
        Intent intent =getIntent();
        Place_id = intent.getStringExtra("place_id");

        text1 = (TextView)findViewById(R.id.text1);
        text2 = (TextView)findViewById(R.id.text2);
        text3 = (TextView)findViewById(R.id.text3);
        text4 = (TextView)findViewById(R.id.text4);
    }
    private class googleplaces extends AsyncTask<View, Void, String> {

        String temp;

        @Override
        protected String doInBackground(View... urls) {
            // make Call to the url
            temp = makeCall("https://maps.googleapis.com/maps/api/place/details/json?placeid="+Place_id+"&key="+GOOGLE_KEY);

            //print the call in the console
            System.out.println("https://maps.googleapis.com/maps/api/place/details/json?placeid="+Place_id+"&key="+GOOGLE_KEY);
            return "";
        }

        @Override
        protected void onPreExecute() {
            // we can start a progress bar here
            progressDialog =new ProgressDialog(Main4Activity.this);
            progressDialog.setMessage("Loading.....Please Wait..");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            if (temp == null) {
                // we have an error to the call
                // we can also stop the progress bar
            } else {
                // all things went right

                // parse Google places search result
                progressDialog.dismiss();
                venuesList = (ArrayList<PlaceDetails>) parseGoogleParse(temp);

                List<String> listTitle = new ArrayList<String>();

                for (int i = 0; i < venuesList.size(); i++) {
                    // make a list of the venus that are loaded in the list.
                    // show the name, the category and the city
                    listTitle.add(i, venuesList.get(i).getName() + "\nAddress: " + venuesList.get(i).getFormatted_address() + "\n local number :" + venuesList.get(i).getFormatted_phone_number() +"\n international number:"+ venuesList.get(i).getInternational_phone_number() );

                }
                text1.setText(venuesList.get(0).getName());
                text2.setText(venuesList.get(0).getFormatted_address());
                text3.setText(venuesList.get(0).getFormatted_phone_number());
                text4.setText(venuesList.get(0).getInternational_phone_number());


                // set the results to the list
                // and show them in the xml
              //  customAdapter = new CustomAdapter(Main4Activity.this, R.layout.newlist_item,  listTitle);
               // lv.setAdapter(customAdapter);
            }
        }
    }

    public static String makeCall(String url) {

        // string buffers the url
        StringBuffer buffer_string = new StringBuffer(url);
        String replyString = "";

        // instanciate an HttpClient
        HttpClient httpclient = new DefaultHttpClient();
        // instanciate an HttpGet
        HttpGet httpget = new HttpGet(buffer_string.toString());

        try {
            // get the responce of the httpclient execution of the url
            HttpResponse response = httpclient.execute(httpget);
            InputStream is = response.getEntity().getContent();

            // buffer input stream the result
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(20);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }
            // the result as a string is ready for parsing
            replyString = new String(baf.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(replyString);

        // trim the whitespaces
        return replyString.trim();
    }

    private static ArrayList<PlaceDetails> parseGoogleParse(final String response) {

        ArrayList<PlaceDetails> temp = new ArrayList<PlaceDetails>();
        try {

            // make an jsonObject in order to parse the response
            JSONObject jsonObject = new JSONObject(response);

            // make an jsonObject in order to parse the response
            if (jsonObject.has("result")) {

                //JSONArray jsonArray = jsonObject.getJSONArray("result");
                JSONObject jsonArray = jsonObject.getJSONObject("result");


                    PlaceDetails poi = new PlaceDetails();
                    if (jsonArray.has("formatted_address")) {
                        poi.setFormatted_address(jsonArray.optString("formatted_address"));
                    }
                    else {
                        poi.setFormatted_address("not available");
                    }
                      if(jsonArray.has("formatted_phone_number")) {
                          poi.setFormatted_phone_number(jsonArray.optString("formatted_phone_number"));
                      }
                    else{
                          poi.setFormatted_phone_number("not available");
                      }
                        if(jsonArray.has("international_phone_number")){
                            poi.setInternational_phone_number(jsonArray.optString("international_phone_number"));
                        }
                        else {poi.setInternational_phone_number("not available");}
                        if(jsonArray.has("name")){
                            poi.setName(jsonArray.optString("name"));
                        }
                        else{poi.setName("name not available");}






                    temp.add(poi);
                }
            } catch (JSONException e1) {
            e1.printStackTrace();

    } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<PlaceDetails>();
        }
        return temp;

    }
}
