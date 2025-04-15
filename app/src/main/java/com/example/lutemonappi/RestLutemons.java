package com.example.lutemonappi;

import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


public class RestLutemons extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_lutemons);
        textView = findViewById(R.id.textViewRest);
        textView.setText("Valitut Lutemonit lepäävät! He saavat täydet elämäpisteet ja ovat valmiina taisteluun!");
    }
}