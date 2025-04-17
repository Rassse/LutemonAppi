package com.example.lutemonappi.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import com.example.lutemonappi.Lutemon;
import com.example.lutemonappi.R;
import com.example.lutemonappi.RestLutemons;
import com.example.lutemonappi.Storage;
import com.example.lutemonappi.TrainLutemons;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private Storage storage;
    private int valueId = 0;
    private CheckBox checkBoxWhite, checkBoxGreen, checkBoxPink, checkBoxOrange, checkBoxBlack;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    private RadioGroup rgLutemonWhereabouts;
    private Lutemon whiteLutemon, greenLutemon, pinkLutemon, orangeLutemon, blackLutemon;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        storage = Storage.getInstance();
        System.out.println(storage);
        Button moveButtonsHome = view.findViewById(R.id.moveButtonsHome);
        moveButtonsHome.setOnClickListener((View v) -> {
            moveLutemons(v);
        });
        ArrayList<Lutemon> lutemons = storage.getLutemons();
        ArrayList<Lutemon> lutemons_here = new ArrayList<>();
        for (Lutemon lutemon : lutemons) {
            if (lutemon.getId() == 1) {
                lutemons_here.add(lutemon);
            }
        }
        checkBoxWhite = view.findViewById(R.id.checkBoxWhite);
        checkBoxGreen = view.findViewById(R.id.checkBoxGreen);
        checkBoxPink = view.findViewById(R.id.checkBoxPink);
        checkBoxOrange = view.findViewById(R.id.checkBoxOrange);
        checkBoxBlack = view.findViewById(R.id.checkBoxBlack);
        rgLutemonWhereabouts = view.findViewById(R.id.rgLutemonHome);
        int checkId = rgLutemonWhereabouts.getCheckedRadioButtonId();
        if (checkId == R.id.radioButtonHomeHome) {
            valueId = 1;
        }
        else if (checkId == R.id.radioButtonTrainHome) {
            valueId = 2;
        }
        else if (checkId == R.id.radioButtonFightHome) {
            valueId = 3;
        }
        // Copilot helped me figure out that I can check lutemons.size() to retrieve the lutemons color right to the checkBoxes //
        if (lutemons.size() > 0) {
            checkBoxWhite.setText(lutemons_here.get(0).getName() + " (" + lutemons_here.get(0).getColor() + ")");
            whiteLutemon = lutemons_here.get(0);
        } else  {
            checkBoxWhite.setVisibility(View.GONE);
        }
        if (lutemons.size() > 1) {
            checkBoxGreen.setText(lutemons_here.get(1).getName() + " (" + lutemons_here.get(1).getColor() + ")");
            greenLutemon = lutemons_here.get(1);
        } else  {
            checkBoxGreen.setVisibility(View.GONE);
        }
        if (lutemons.size() > 2) {
            checkBoxPink.setText(lutemons_here.get(2).getName()+" ("+lutemons_here.get(2).getColor()+")");
            pinkLutemon = lutemons_here.get(2);
        } else {
            checkBoxPink.setVisibility(View.GONE);
        }
        if (lutemons.size() > 3) {
            checkBoxOrange.setText(lutemons_here.get(3).getName()+" ("+lutemons_here.get(3).getColor()+")");
            orangeLutemon = lutemons_here.get(3);
        }
        else {
            checkBoxOrange.setVisibility(View.GONE);
        }
        if (lutemons.size() > 4) {
            checkBoxBlack.setText(lutemons_here.get(4).getName()+" ("+lutemons_here.get(4).getColor()+")");
            blackLutemon = lutemons_here.get(4);
        }
        else {
            checkBoxBlack.setVisibility(View.GONE);
        }

        return view;
    }


    public void moveLutemons(View view) {
        // I learned from Copilot to check the id of the lutemon, not go through long if else clauses //
        // Copilot helped me to only create one function, rather than what I had, three functions here doing basically the same thing //
        // The functions were just messy and redundant //
        int location = 0;
        int checkId = rgLutemonWhereabouts.getCheckedRadioButtonId();
        if (checkId == R.id.radioButtonHomeHome) {
            location = 1;
        }
        else if (checkId == R.id.radioButtonTrainHome) {
            location = 2;
        }
        else if (checkId == R.id.radioButtonFightHome) {
            location = 3;
        }
        if (checkBoxWhite.isChecked()) {
            whiteLutemon.setId(location);
        }
        if (checkBoxGreen.isChecked()) {
            greenLutemon.setId(location);
        }
        if (checkBoxPink.isChecked()) {
            pinkLutemon.setId(location);
        }
        if (checkBoxOrange.isChecked()) {
            orangeLutemon.setId(location);
        }
        if (checkBoxBlack.isChecked()) {
            blackLutemon.setId(location);
        }
        storage.saveLutemons(getContext());

        if (location == 1) {
            Intent intent = new Intent(getActivity(), RestLutemons.class);
            startActivity(intent);
        }
        else if (location == 2 ) {
            Intent intent = new Intent(getActivity(), TrainLutemons.class);
            startActivity(intent);
        }
        else if (location == 3) {
            /* Intent intent = new Intent(getActivity(), FightLutemons.class);
            startActivity(intent); */
        }

    }
    // https://stackoverflow.com/questions/11326155/fragment-onresume-onpause-is-not-called-on-backstack //
    @Override
    public void onResume() {
        super.onResume();
    }

}