package com.example.lutemonappi;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FightActivity extends AppCompatActivity {

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
        storage.listLutemonsInformation();



    }
}