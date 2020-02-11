package com.example.travel360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Survey extends AppCompatActivity {

    Dictionary<String, String> answers = new Hashtable<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        Button submitSurvey=(Button) findViewById(R.id.submitButton);

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("assets/locations.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            Log.d("radio", "it worked=================");
        } catch (IOException ex) {
            ex.printStackTrace();
            Log.d("radio", "oops=================");
            return null;
        }
        return json;
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
            answers.put(question, response);
            //answers.add(radioButton.getText().toString());


            //group1.text

        }
        Log.d("radio", loadJSONFromAsset());

        Intent intent = new Intent(Survey.this, Submit.class);
        //intent.putStringArrayListExtra("questions", answers);
        startActivity(intent);



    }

}


