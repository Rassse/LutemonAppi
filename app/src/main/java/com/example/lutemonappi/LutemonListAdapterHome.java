package com.example.lutemonappi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LutemonListAdapterHome extends RecyclerView.Adapter<LutemonViewHolderHome> {
    private Context context;
    private ArrayList<Lutemon> lutemons;

    public LutemonListAdapterHome(Context context, ArrayList<Lutemon> lutemons) {
        this.context = context;
        this.lutemons = lutemons;
    }

    @NonNull
    @Override
    public LutemonViewHolderHome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonViewHolderHome(LayoutInflater.from(context).inflate(R.layout.lutemonhome_view, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolderHome holder, int position) {
        holder.homeName.setText(lutemons.get(position).getName() + " (" + lutemons.get(position).getColor()+ ") ");
        holder.homeAttack.setText("Hyökkäys: " + lutemons.get(position).getAttack());
        holder.homeDefence.setText("Puolustus: " + lutemons.get(position).getDefense());
        holder.homeHealth.setText("Elämä: " + lutemons.get(position).getHealth());
        holder.homeExperience.setText("Kokemus: " + lutemons.get(position).getExperience());
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
