package com.example.lutemonappi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FightActivity extends AppCompatActivity {

    private TextView textView;
    private Button buttonFightToNavigator;
    ArrayList<Lutemon> lutemonsInFight = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        Storage storage = Storage.getInstance();
        for (Lutemon lutemon : storage.getLutemons()) {
            if (lutemon.getId() == 5) {
                lutemonsInFight.add(lutemon);
            }
        }
        if (lutemonsInFight.size() == 2) {
            textView = findViewById(R.id.textViewFight);
            textView.setText("Lutemonit taistelevat ja ottavat mittaa toisistaan!!!" + "\n");
            for (Lutemon lutemon : lutemonsInFight) {
                textView.append("Lutemoni: " + lutemon.getColor() + "(" + lutemon.getName() + ")" + " hyök: " + lutemon.getAttack() + "; puol: " + lutemon.getDefense() + "; kok: " + lutemon.getExperience() + "; elämät: " + lutemon.getHealth() + "\n");
            }
            Lutemon lutemon1 = lutemonsInFight.get(0);
            Lutemon lutemon2 = lutemonsInFight.get(1);
            while (lutemon1.getHealth() > 0 && lutemon2.getHealth() > 0) {
                String attack1 = lutemon1.attack(lutemon2);
                textView.append(attack1 + "\n");
                if (lutemon2.getHealth() <= 0) {
                    break;
                }
                String defence1 = lutemon2.defence(lutemon1);
                textView.append(defence1 + "\n");
                String attack2 = lutemon2.attack(lutemon1);
                textView.append(attack2 + "\n");

                if (lutemon1.getHealth() <= 0) {
                    break;
                }
                String defence2 = lutemon1.defence(lutemon2);
                textView.append(defence2 + "\n");
            }

            storage.saveLutemons(this);
            if (lutemon1.getHealth() <= 0) {
                lutemon1.setId(4);
                lutemon2.setId(1);
            } else if (lutemon2.getHealth() <= 0) {
                lutemon2.setId(4);
                lutemon1.setId(1);
            }

        }
    }

        public void switchToNavigator (View view) {
            Storage storage = Storage.getInstance();
            // Copilot suggested to me to clear the list after fight //
            for (Lutemon lutemon : lutemonsInFight) {
                if (lutemon.getHealth() <= 0) {
                    lutemon.setId(4);
                } else {
                    lutemon.setId(1);
                }
            }
            storage.saveLutemons(this);
            lutemonsInFight.clear();

            Intent intent = new Intent(this, ActivityNavigator.class);
            startActivity(intent);
        }

        @Override
        public void onResume () {
            super.onResume();
        }
    }