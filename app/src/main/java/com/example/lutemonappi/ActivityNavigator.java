package com.example.lutemonappi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class ActivityNavigator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);

    }

    public void switchToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void switchToListLutemons(View view) {
        Intent intent = new Intent(this, LutemonListActivity.class);
        startActivity(intent);
    }

    public void switchToMoveLutemons(View view) {
        Intent intent = new Intent(this, MoveLutemons.class);
        startActivity(intent);
    }

}