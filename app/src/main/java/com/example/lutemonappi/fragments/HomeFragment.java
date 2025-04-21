package com.example.lutemonappi.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import com.example.lutemonappi.FightActivity;
import com.example.lutemonappi.Lutemon;
import com.example.lutemonappi.R;
import com.example.lutemonappi.RestLutemons;
import com.example.lutemonappi.Storage;
import com.example.lutemonappi.TrainLutemons;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // I learned from Copilot to create a list for checkboxes //
    private List<CheckBox> checkBoxList = new ArrayList<>();
    private Storage storage;
    private int valueId = 1;
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
        return inflater.inflate(R.layout.fragment_home, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        storage = Storage.getInstance();
        System.out.println(storage);
        Button moveButtonsHome = view.findViewById(R.id.moveButtonsHome);
        moveButtonsHome.setOnClickListener((View v) -> {
            moveLutemons(v);
        });
        ArrayList<Lutemon> lutemons = storage.getLutemons();
        ArrayList<Lutemon> lutemons_here = new ArrayList<>();
        ConstraintLayout constraintLayout = view.findViewById(R.id.constraintLayoutId);
        for (Lutemon lutemon : lutemons) {
            if (lutemon.getId() == 1) {
                lutemons_here.add(lutemon);
                CheckBox checkBox = new CheckBox(getContext());
                checkBox.setText(lutemon.getName() + " ("+lutemon.getColor()+")");
                // Copilot helped me to generate unique ID I can access later on //
                checkBox.setId(View.generateViewId());
                checkBoxList.add(checkBox);
            }
        }

        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isChecked()) {
                System.out.println(checkBox.getText());
            }
        }
        /*
        checkBoxWhite = view.findViewById(R.id.checkBoxWhite);
        checkBoxGreen = view.findViewById(R.id.checkBoxGreen);
        checkBoxPink = view.findViewById(R.id.checkBoxPink);
        checkBoxOrange = view.findViewById(R.id.checkBoxOrange);
        checkBoxBlack = view.findViewById(R.id.checkBoxBlack);
        */

        /*
        // Copilot helped me figure out that I can check lutemons.size() to retrieve the lutemons color right to the checkBoxes //
        if (lutemons_here.size() > 0) {
            checkBoxWhite.setText(lutemons_here.get(0).getName() + " (" + lutemons_here.get(0).getColor() + ")");
            whiteLutemon = lutemons_here.get(0);
        } else  {
            checkBoxWhite.setVisibility(View.GONE);
        }
        if (lutemons_here.size() > 1) {
            checkBoxGreen.setText(lutemons_here.get(1).getName() + " (" + lutemons_here.get(1).getColor() + ")");
            greenLutemon = lutemons_here.get(1);
        } else  {
            checkBoxGreen.setVisibility(View.GONE);
        }
        if (lutemons_here.size() > 2) {
            checkBoxPink.setText(lutemons_here.get(2).getName()+" ("+lutemons_here.get(2).getColor()+")");
            pinkLutemon = lutemons_here.get(2);
        } else {
            checkBoxPink.setVisibility(View.GONE);
        }
        if (lutemons_here.size() > 3) {
            checkBoxOrange.setText(lutemons_here.get(3).getName()+" ("+lutemons_here.get(3).getColor()+")");
            orangeLutemon = lutemons_here.get(3);
        }
        else {
            checkBoxOrange.setVisibility(View.GONE);
        }
        if (lutemons_here.size() > 4) {
            checkBoxBlack.setText(lutemons_here.get(4).getName()+" ("+lutemons_here.get(4).getColor()+")");
            blackLutemon = lutemons_here.get(4);
        }
        else {
            checkBoxBlack.setVisibility(View.GONE);
        }
        */
    }


    public void moveLutemons(View view) {
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

        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isChecked()) {
                for (Lutemon lutemon : storage.getLutemons()) {
                    if (checkBox.getText().toString().contains(lutemon.getName())) {
                        lutemon.setId(valueId);
                    }
                }
            }
        }

        storage.saveLutemons(getContext());



        /*
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
        */
        // COpilot helped me to initialize intent here //

        Intent intent = null;

        if (valueId== 1) {
            intent = new Intent(getActivity(), RestLutemons.class);
        }
        else if (valueId == 2 ) {
            intent = new Intent(getActivity(), TrainLutemons.class);
        }
        else if (valueId== 3) {
            intent = new Intent(getActivity(), FightActivity.class);
        }
        else if (valueId == 4) {
            intent = new Intent(getActivity(), DeadFragment.class);
        }
        // Copilot helped me to debug that if I check intent != null it will solve some issues //
        if (intent != null) {
            startActivity(intent);
        }



    }
    // https://stackoverflow.com/questions/11326155/fragment-onresume-onpause-is-not-called-on-backstack //
    @Override
    public void onResume() {
        super.onResume();
    }

}