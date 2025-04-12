package com.example.lutemonappi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void addLutemon(View view) {
        EditText name = findViewById(R.id.editText);
        RadioGroup rgLutemonType = findViewById(R.id.rgLutemonType);
        int checkId = rgLutemonType.getCheckedRadioButtonId();
        /* DeepSeek helped me to debug this part with the help of lecture material, I had to make a checkId variable and check the conditions based on that */
        if (checkId == R.id.radioButtonWhite) {
            Storage.getInstance().addLutemon(new Lutemon(name.getText().toString(), "Valkoinen"));
            switchToActivityNavigator(view);
        }
        else if (checkId == R.id.radioButtonGreen) {
            Storage.getInstance().addLutemon(new Lutemon(name.getText().toString(), "Vihreä"));
            switchToActivityNavigator(view);
        }
        else if (checkId == R.id.radioButtonPink) {
            Storage.getInstance().addLutemon(new Lutemon(name.getText().toString(), "Pinkki"));
            switchToActivityNavigator(view);
        }
        else if (checkId == R.id.radioButtonOrange) {
            Storage.getInstance().addLutemon(new Lutemon(name.getText().toString(), "Oranssi"));
            switchToActivityNavigator(view);
        }
        else if (checkId == R.id.radioButtonBlack) {
            Storage.getInstance().addLutemon(new Lutemon(name.getText().toString(), "Musta"));
            switchToActivityNavigator(view);
        }

    }
    public void switchToActivityNavigator(View view) {
        Intent intent = new Intent(this, ActivityNavigator.class);
        startActivity(intent);
    }
}