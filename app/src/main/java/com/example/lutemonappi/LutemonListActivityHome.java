package com.example.lutemonappi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonListActivityHome extends AppCompatActivity {

    private Storage storage;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lutemon_list_home);

        storage = Storage.getInstance();
        // recyclerView = findViewById(R.id.rvLutemonsHome);
        recyclerView.setAdapter(new LutemonListAdapter(getApplicationContext(), storage.getLutemons()));

    }
}