package com.example.lutemonappi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class TrainLutemons extends AppCompatActivity {
    private Storage storage;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_lutemons);
        Button button3 = findViewById(R.id.button3);
        textView = findViewById(R.id.textViewTrain);
        // I asked Copilot what was the command for setting text because I forgot it //
        textView.setText("Valitut Lutemonit treenattu! He saivat 2 Hyökkäyspistettä ja ovat valmiita taisteluun!");
        storage = Storage.getInstance();
        ArrayList<Lutemon> lutemons = storage.getLutemons();
        for (Lutemon lutemon : lutemons) {
            if (lutemon.getId() == 2) {
                lutemon.setAttack(lutemon.getAttack() + 2);
            }
        }
        storage.saveLutemons(this);
    }

    public void switchToFragments(View view) {
        Intent intent = new Intent(this, TabActivity.class);
        startActivity(intent);
    }
}