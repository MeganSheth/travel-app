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
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
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
        Drawable image = (Drawable)LoadImageFromWebOperations("https://cdn.britannica.com/43/100843-050-AAF9A927/Cancun-Mex.jpg");
        ImageView cancun = (ImageView)findViewById(R.id.topimage1);
        cancun.setImageDrawable(image);
        Log.d("string", "Done");
        //int Image= Integer.parseInt("R.drawable." + finallocation.get(0));
        //cancun.setImage(R.drawable.);
        //cancun.setImageResource(Image);
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
