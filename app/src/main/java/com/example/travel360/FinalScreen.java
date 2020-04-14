package com.example.travel360;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import android.app.Activity;




public class FinalScreen extends AppCompatActivity{
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        HashMap<Integer, String> finallocation = (HashMap<Integer, String>)getIntent().getSerializableExtra("map");
        Log.d("key", String.valueOf(finallocation));
        Log.d("string", String.valueOf(finallocation.get(0)));
        Log.d("string", String.valueOf(finallocation.get(1)));
        Log.d("string", String.valueOf(finallocation.get(2)));
        setContentView(R.layout.activity_finalscreen);
        TextView places1 = (TextView) findViewById(R.id.textView1);
        TextView places2 = (TextView) findViewById(R.id.textView2);
        TextView places3 = (TextView) findViewById(R.id.textView3);
        places1.setText(finallocation.get(0));
        places2.setText(finallocation.get(1));
        places3.setText(finallocation.get(2));
        String url1 = GetImage(finallocation.get(0));
        Drawable image = (Drawable)LoadImageFromWebOperations(url1);
        ImageView location = (ImageView)findViewById(R.id.topimage1);
        location.setImageDrawable(image);
        String url2 = GetImage(finallocation.get(1));
        image = (Drawable)LoadImageFromWebOperations(url2);
        location = (ImageView)findViewById(R.id.topimage2);
        location.setImageDrawable(image);
        String url3 = GetImage(finallocation.get(2));
        image = (Drawable)LoadImageFromWebOperations(url3);
        location = (ImageView)findViewById(R.id.topimage3);
        location.setImageDrawable(image);
        Log.d("string", "Done");
        //int Image= Integer.parseInt("R.drawable." + finallocation.get(0));
        //cancun.setImage(R.drawable.);
        //cancun.setImageResource(Image);
        String json = loadJSONFromAsset();
        JSONArray jsonArray = null;

        try {
            JSONObject jsonObject = new JSONObject(json);
            jsonArray = jsonObject.getJSONArray("locations");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject locations = jsonArray.getJSONObject(i);
                String urls = (String)locations.get("description");

                if ((locations.get("location")).equals(finallocation.get(0))){
                    Log.d("string", urls);
                    TextView location1 = (TextView)findViewById(R.id.textView4);
                    location1.setText(urls);
                }
                if ((locations.get("location")).equals(finallocation.get(1))){
                    Log.d("string", urls);
                    TextView location2 = (TextView)findViewById(R.id.textView5);
                    location2.setText(urls);
                }
                if ((locations.get("location")).equals(finallocation.get(2))){
                    Log.d("string", urls);
                    TextView location3 = (TextView)findViewById(R.id.textView6);
                    location3.setText(urls);
                }

            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
    public String loadJSONFromAsset() {
        AssetManager assetManager = getResources().getAssets();
        InputStream inputStream = null;
        String json = null;
        try {
            inputStream = assetManager.open("locations.json");
            if ( inputStream != null)
                Log.d("string", "It worked!");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "hey";
    }

    public String GetImage(String location){

        URL url;
        String stringurl = "";
        HttpURLConnection urlConnection = null;
        try {
            Log.d("string", "finish");
            url = new URL("https://api.unsplash.com/search/photos?query=" + location + "&per_page=2&client_id=uPJ5buVADNSBTTKwZkpNboLOrMM3zySMXj5EZb2TTBY");

            urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);
            String inputdata = readStream(in);
            JSONObject response = new JSONObject(inputdata);
            JSONArray results = response.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject imagedata = results.getJSONObject(i);
                JSONObject urls = (JSONObject)imagedata.get("urls");
                Log.d("string", imagedata.toString());
                String imageurls = (String)urls.get("small");
                Log.d("string", imageurls);
                stringurl = imageurls;
            }
            Log.d("string", inputdata);
        } catch (Exception e) {
            Log.d("string", "==========");
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return stringurl;
    }
    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            Log.d("string", e.toString());
            Log.d("string","==============================");
            return null;
        }
    }


}
