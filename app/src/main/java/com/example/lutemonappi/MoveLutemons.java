package com.example.lutemonappi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.lutemonappi.fragments.DeadFragment;
import com.example.lutemonappi.fragments.FightFragment;
import com.example.lutemonappi.fragments.HomeFragment;
import com.example.lutemonappi.fragments.TrainingFragment;


public class MoveLutemons extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_lutemons);
        // Copilot helped me to figure out to put the Intent here in order for the tabs to be constantly on, not get inititialized by pressing the one of the tabs //
        Intent intent = new Intent(this, TabActivity.class);
        startActivity(intent);

        Button homeFragment = findViewById(R.id.buttonHome);
        Button trainingFragment = findViewById(R.id.buttonTraining);
        Button fightFragment = findViewById(R.id.buttonFight);
        Button deadFragment = findViewById(R.id.buttonDead);

        homeFragment.setOnClickListener(listener);
        trainingFragment.setOnClickListener(listener);
        fightFragment.setOnClickListener(listener);
        deadFragment.setOnClickListener(listener);

    }
    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            // Copilot helped me figure out I had to remove the intent to not go directlty to the first activity //
            Fragment fragment = null;
            int checkId = view.getId();
            if (checkId == R.id.buttonHome) {
                fragment = new HomeFragment();
            }
            else if (checkId == R.id.buttonTraining) {
                fragment = new TrainingFragment();
            }
            else if (checkId == R.id.buttonFight) {
                fragment = new FightFragment();
            }
            else if (checkId == R.id.buttonDead) {
                fragment = new DeadFragment();
            }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, fragment)
                        .commit();
        }
        
    };
}