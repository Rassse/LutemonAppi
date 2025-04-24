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
            if (lutemon.getId() == 5) {
                lutemonsInFight.add(lutemon);
            }
        }
        textView = findViewById(R.id.textViewFight);
        textView.setText("Lutemonit taistelevat ja ottavat mittaa toisistaan!!!");
        textView.append(storage.listLutemonsInformation()+"\n");
        Lutemon lutemon1 = lutemonsInFight.get(0);
        Lutemon lutemon2 = lutemonsInFight.get(1);
        String attack = lutemon1.attack(lutemon2);
        textView.append(attack+"\n");
        String defence = lutemon2.defence(lutemon1);
        textView.append(defence+"\n");
    }
}