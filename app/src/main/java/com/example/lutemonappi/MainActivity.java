package com.example.lutemonappi;

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
        switch (rgLutemonType.getCheckedRadioButtonId()) {
            case R.id.radioButtonWhite:
                Storage.getInstance().addLutemon(new Lutemon(name.getText().toString(), "Valkoinen"));
            case R.id.radioButtonGreen:
                Storage.getInstance().addLutemon(new Lutemon(name.getText().toString(), "Vihreä"));
            case R.id.radioButtonPink:
                Storage.getInstance().addLutemon(new Lutemon(name.getText().toString(), "Pinkki"));
            case R.id.radioButtonOrange:
                Storage.getInstance().addLutemon(new Lutemon(name.getText().toString(), "Oranssi"));
            case R.id.radioButtonBlack:
                Storage.getInstance().addLutemon(new Lutemon(name.getText().toString(), "Musta"));
        }
    }
}