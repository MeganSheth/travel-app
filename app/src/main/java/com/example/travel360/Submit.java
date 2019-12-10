package com.example.travel360;


import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class Submit extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.destinations);
        Intent intent = getIntent();
        ArrayList<String> answers = intent.getStringArrayListExtra("questions");
        for (String answer: answers) {
            Log.d("answers", answer);

    }
}}
