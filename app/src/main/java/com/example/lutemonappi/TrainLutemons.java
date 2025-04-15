package com.example.lutemonappi;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class TrainLutemons extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_lutemons);

        textView = findViewById(R.id.textViewTrain);
        // I asked Copilot what was the command for setting text because I forgot it //
        textView.setText("Valitut Lutemonit treenattu! He saivat 2 Hyökkäyspistettä ja ovat valmiita taisteluun!");
    }
}