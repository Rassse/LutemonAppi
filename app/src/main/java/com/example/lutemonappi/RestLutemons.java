package com.example.lutemonappi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class RestLutemons extends AppCompatActivity {
    TextView textView;
    private Storage storage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_lutemons);
        Button button2 = findViewById(R.id.button2);
        textView = findViewById(R.id.textViewRest);
        textView.setText("Valitut Lutemonit lepäävät! He saavat täydet elämäpisteet ja ovat valmiina taisteluun!");
        storage = Storage.getInstance();
        ArrayList<Lutemon> lutemons = storage.getLutemons();
        for (Lutemon lutemon : lutemons) {
            if (lutemon.getId() == 1) {
                lutemon.setHealth(lutemon.getMaxHealth());
            }
        }
        storage.saveLutemons(this);
    }

    public void switchToMainTab(View view) {
        Intent intent = new Intent(this, TabActivity.class);
        startActivity(intent);
    }
}