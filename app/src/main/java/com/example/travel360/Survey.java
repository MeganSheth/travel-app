package com.example.travel360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Survey extends AppCompatActivity {

    Dictionary<String, String> answers = new Hashtable<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        Button submitSurvey=(Button) findViewById(R.id.submitButton);

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

    public void submitSurvey(View view) {
        Log.d("string", "stringtwo");
        RadioGroup group1 = (RadioGroup)findViewById(R.id.radioGroup1);
        TextView text1 = (TextView)findViewById(R.id.textView3);
        Log.d("text", text1.getText().toString());
        ArrayList<RadioGroup> listOfRadioGroups = new ArrayList<RadioGroup>();
        listOfRadioGroups.add((RadioGroup)findViewById(R.id.radioGroup1));
        RadioGroup test = findViewById(R.id.radioGroup1);
        int radioButtonID1 = test.getCheckedRadioButtonId();
        RadioButton answer = (RadioButton)test.findViewById(radioButtonID1);
        Log.d("temp", answer.getTag().toString());
        listOfRadioGroups.add((RadioGroup)findViewById(R.id.radioGroup2));
        listOfRadioGroups.add((RadioGroup)findViewById(R.id.radioGroup5));
        listOfRadioGroups.add((RadioGroup)findViewById(R.id.radioGroup4));
        listOfRadioGroups.add((RadioGroup)findViewById(R.id.radioGroup6));
        listOfRadioGroups.add((RadioGroup)findViewById(R.id.radioGroup7));
        listOfRadioGroups.add((RadioGroup)findViewById(R.id.radioGroup8));
        listOfRadioGroups.add((RadioGroup)findViewById(R.id.radioGroup9));
        listOfRadioGroups.add((RadioGroup)findViewById(R.id.radioGroup10));
        listOfRadioGroups.add((RadioGroup)findViewById(R.id.radioGroup11));
        for (RadioGroup group : listOfRadioGroups) {
            String question = group.getTag().toString();
            int radioButtonID = group.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) group.findViewById(radioButtonID);
            String response = radioButton.getTag().toString();
            Log.d("string", question);
            answers.put(question, response);
        }
        String json = loadJSONFromAsset();
        JSONArray jsonArray = null;

        try {
            JSONObject jsonObject = new JSONObject(json);
            jsonArray = jsonObject.getJSONArray("locations");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        Map <String, Integer> output = new HashMap<String, Integer>();
        if (jsonArray != null){
            Log.d("string", "Array isn't empty");
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject explrObject = jsonArray.getJSONObject(i);
                    String location = explrObject.getString("location");
                    Log.d("string", location);
                    JSONObject characteristics = explrObject.getJSONObject("characteristics");
                    Iterator<String> iter = characteristics.keys();
                    int count = 0;
                    while (iter.hasNext()) {
                        String key = iter.next();
                        try {
                            int characteristicValue = (int) characteristics.get(key);
                            String answerString = answers.get(key);
                            int answerValue = Integer.valueOf(answerString);
                            if (characteristicValue == answerValue) {
                                count++;
                            }
                        } catch (JSONException e) {
                            Log.d("string", "oof");
                        }
                    }
                    output.put(location, count);
                    // Array(location : count) to hashtable
                    Log.d("integar", "value: " + count);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }

        int max =  0;
        for (Map.Entry<String, Integer> entry : output.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();

            }
            Log.d("int", String.valueOf(max));
            Log.d("string", String.valueOf(output));
        }

        //Intent intent = new Intent(Survey.this, Submit.class);
        //intent.putStringArrayListExtra("questions", answers);
        //startActivity(intent);

    }





}




