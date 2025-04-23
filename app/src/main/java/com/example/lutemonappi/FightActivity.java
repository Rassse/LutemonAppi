package com.example.lutemonappi;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FightActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        Storage storage = Storage.getInstance();
        ArrayList<Lutemon> lutemonsInFight = new ArrayList<>();
        for (Lutemon lutemon : storage.getLutemons()) {
            if (lutemon.getId() == 3) {
                lutemonsInFight.add(lutemon);
            }
        }
        textView = findViewById(R.id.textViewFight);
        textView.setText("Lutemonit taistelevat ja ottavat mittaa toisistaan!!!");
        storage.listLutemonsInformation();
        for (Lutemon lutemon : lutemonsInFight) {
            /*textView.append("\n"+ toString(storage.listLutemonsInformation())); */
            lutemon.attack(lutemonsInFight.get(0));
            lutemon.defence(lutemonsInFight.get(0));
        }
    }
}