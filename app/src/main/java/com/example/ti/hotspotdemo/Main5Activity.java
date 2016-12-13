package com.example.ti.hotspotdemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Main5Activity extends AppCompatActivity {
    ArrayList<NewItem> venuesList;
    CustomAdapter customAdapter;

    ListView lv;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lv = (ListView)findViewById(R.id.list);
        //lv = getListView();
        // start the AsyncTask that makes the call for the venus search.
        new googleplaces().execute();


    }

    private class googleplaces extends AsyncTask<View, Void, String> {
        private String msearchstr;
        String temp;

        private String mSearchStr = "ife";
        private int mNumOfResults = 10;




        @Override
        protected String doInBackground(View... urls) {

            String searchStr = URLEncoder.encode(mSearchStr);
            String numOfResultsStr = mNumOfResults <= 0 ? "" : "&$top=" + mNumOfResults;
            String bingUrl = "https://api.datamarket.azure.com/Bing/SearchWeb/Web?Query=%27" + searchStr + "%27" + numOfResultsStr + "&$format=json";
            String accountKey = "vdGa3eGn2WsdQan/PMNUYBpcm7d+BhCWWwuNq9WETzl";
            byte[] accountKeyBytes = Base64.encodeBase64((accountKey + ":" + accountKey).getBytes());
            String accountKeyEnc = new String(accountKeyBytes);

//            e.printStackTrace();
//            }  URL url = null;
//            try {
//                url = new URL(bingUrl);
//            } catch (MalformedURLException e) {
//

            // make Call to the url
            try {
                temp = makeCall();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //print the call in the console
            System.out.println();
            return "";
        }

        @Override
        protected void onPreExecute() {
            // we can start a progress bar here
            progressDialog =new ProgressDialog(Main5Activity.this);
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
                venuesList = (ArrayList<NewItem>) parseGoogleParse(temp);

                List<String> listTitle = new ArrayList<String>();

                for (int i = 0; i < venuesList.size(); i++) {
                    // make a list of the venus that are loaded in the list.
                    // show the name, the category and the city
                    listTitle.add(i, venuesList.get(i).getName() + "\n" + venuesList.get(i).getDescription());

                }


                // set the results to the list
                // and show them in the xml
                try{
                customAdapter = new CustomAdapter(Main5Activity.this, R.layout.newlist_item, listTitle);
                lv.setAdapter(customAdapter);}
                catch (NullPointerException e){
                    e.printStackTrace();
                    Toast.makeText(Main5Activity.this, getString(R.string.Network), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public static String makeCall() throws IOException {
         String msearchstr;
        String temp;

         String mSearchStr = "osun";
         int mNumOfResults = 10;

        String searchStr = URLEncoder.encode(mSearchStr);
        String numOfResultsStr = mNumOfResults <= 0 ? "" : "&$top=" + mNumOfResults;
        String bingUrl = "https://api.datamarket.azure.com/Bing/SearchWeb/Web?Query=%27" + searchStr + "%27" + numOfResultsStr + "&$format=json";
        String accountKey = "vdGa3eGn2WsdQan/PMNUYBpcm7d+BhCWWwuNq9WETzl";
        byte[] accountKeyBytes = Base64.encodeBase64((accountKey + ":" + accountKey).getBytes());
        String accountKeyEnc = new String(accountKeyBytes);


              URL ur = null;
            try {
                ur = new URL(bingUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


         // string buffers the url
        //StringBuffer buffer_string = new StringBuffer(url);
         String replyString = "";

        URLConnection urlConnection = ur.openConnection();
        urlConnection.setRequestProperty("Authorization", "Basic " + accountKeyEnc);
        // instanciate an HttpClient
        //HttpClient httpclient = new DefaultHttpClient();
        // instanciate an HttpGet
        // HttpGet httpget = new HttpGet(buffer_string.toString());

        try {
            // get the responce of the httpclient execution of the url
          //  HttpResponse response = httpclient.execute(httpget);
           // InputStream is = response.getEntity().getContent();
            InputStream is = urlConnection.getInputStream();

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

    private static ArrayList<NewItem> parseGoogleParse(final String response) {

        ArrayList<NewItem> temp = new ArrayList<NewItem>();
        try {

            // make an jsonObject in order to parse the response
            JSONObject jsonObject = new JSONObject(response);

            // make an jsonObject in order to parse the response
            if (jsonObject.has("value")) {

                JSONArray jsonArray = jsonObject.getJSONArray("value");

                for (int i = 0; i < jsonArray.length(); i++) {
                    NewItem poi = new NewItem();
                    if (jsonArray.getJSONObject(i).has("name")) {
                        poi.setName(jsonArray.getJSONObject(i).optString("name"));
                        poi.setUrl(jsonArray.getJSONObject(i).optString("url"));

                        if (jsonArray.getJSONObject(i).has("description")) {
                            poi.setDescription(jsonArray.getJSONObject(i).optString("description"));
                        } else {
                            poi.setDescription(jsonArray.getJSONObject(i).optString("News not available"));
                        }

                        temp.add(poi);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<NewItem>();
        }
        return temp;

    }
}
