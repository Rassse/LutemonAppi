package com.example.lutemonappi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonViewHolderHome extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView homeName, homeAttack, homeDefence, homeHealth, homeExperience;
    public LutemonViewHolderHome(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageViewHome);
        homeName = itemView.findViewById(R.id.textViewHomeName);
        homeAttack = itemView.findViewById(R.id.textViewHomeAttack);
        homeDefence = itemView.findViewById(R.id.textViewHomeDefence);
        homeHealth = itemView.findViewById(R.id.textViewHomeHealth);
        homeExperience = itemView.findViewById(R.id.textViewHomeExperience);
    }
}
