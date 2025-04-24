package com.example.lutemonappi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView lutemonName, lutemonAttack, lutemonDefence, lutemonHealth, lutemonExperience, lutemonLosses;
    public LutemonViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        lutemonName = itemView.findViewById(R.id.textViewName);
        lutemonAttack = itemView.findViewById(R.id.textViewAttack);
        lutemonDefence = itemView.findViewById(R.id.textViewDefence);
        lutemonHealth = itemView.findViewById(R.id.textViewHealth);
        lutemonExperience = itemView.findViewById(R.id.textViewExperience);
        lutemonLosses = itemView.findViewById(R.id.textViewLosses);

    }
}
