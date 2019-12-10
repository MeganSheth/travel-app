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

import java.util.ArrayList;

public class Survey extends AppCompatActivity {

    private ArrayList<String> answers = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        Button submitSurvey=(Button) findViewById(R.id.submitButton);




    }

    public void submitSurvey(View view) {
        Log.d("string", "stringtwo");
        RadioGroup group1 = (RadioGroup)findViewById(R.id.radioGroup1);
        TextView text1 = (TextView)findViewById(R.id.textView3);
        Log.d("text", text1.getText().toString());
        ArrayList<RadioGroup> listOfRadioGroups = new ArrayList<RadioGroup>();
        listOfRadioGroups.add((RadioGroup)findViewById(R.id.radioGroup1));
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
            int radioButtonID = group.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton)group.findViewById(radioButtonID);
            answers.add(radioButton.getText().toString());
            Log.d("radio", radioButton.getText().toString());


            //group1.text
        }
        Intent intent = new Intent(Survey.this, Submit.class);
        intent.putStringArrayListExtra("questions", answers );
        startActivity(intent);

    }

}


