package com.example.lutemonappi.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.example.lutemonappi.Lutemon;
import com.example.lutemonappi.R;
import com.example.lutemonappi.RestLutemons;
import com.example.lutemonappi.Storage;
import com.example.lutemonappi.TrainLutemons;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrainingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrainingFragment extends Fragment {

    private List<CheckBox> checkBoxList = new ArrayList<>();
    private Storage storage;
    private int valueId = 1;
    private LinearLayout linearLayoutCheckBox;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    private RadioGroup rgLutemonWhereAbouts;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TrainingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrainingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrainingFragment newInstance(String param1, String param2) {
        TrainingFragment fragment = new TrainingFragment();
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
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        storage = Storage.getInstance();
        lutemons = storage.getLutemons();
        Button moveButtonsTrain = view.findViewById(R.id.moveButtonsTrain);
        moveButtonsTrain.setOnClickListener((View v) -> {
            moveLutemons(v);
        });
        rgLutemonWhereAbouts = view.findViewById(R.id.rgTraining);
        // Copilot helped me to check Id through iteration rather than long if else clauses //
        ArrayList<Lutemon> lutemons_here = new ArrayList<>();
        linearLayoutCheckBox = view.findViewById(R.id.linearLayoutTrain);
        for (Lutemon lutemon : lutemons) {
            if (lutemon.getId() == 2) {
                lutemons_here.add(lutemon);
            }
        }
        for (Lutemon lutemon : lutemons_here) {
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(lutemon.getName() + " ("+lutemon.getColor()+")");
            // Copilot helped me to generate unique ID I can access later on //
            checkBox.setId(View.generateViewId());
            checkBoxList.add(checkBox);
            linearLayoutCheckBox.addView(checkBox);
        }


        return view;
    }

    public void moveLutemons(View view) {
        // Copilot helped me to create error handling for avoiding null crash when moving lutemons from trainingfragment//
        if (rgLutemonWhereAbouts == null) {
            rgLutemonWhereAbouts = view.findViewById(R.id.rgTraining);
        }
        int checkId = rgLutemonWhereAbouts.getCheckedRadioButtonId();
        if (checkId == R.id.radioButtonHomeHome) {
            valueId = 1;
        } else if (checkId == R.id.radioButtonTrainHome) {
            valueId = 2;
        } else if (checkId == R.id.radioButtonFightHome) {
            valueId = 3;
        }
        // COpilot helped me to create a new arraylist so that I can remove checkboxes and the fragment doesn't crash //
        ArrayList<CheckBox> removeCheckBoxes = new ArrayList<>();
        ArrayList<Lutemon> moveLutemons = new ArrayList<>();
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isChecked()) {
                for (Lutemon lutemon : storage.getLutemons()) {
                    if (checkBox.getText().toString().contains(lutemon.getName())) {
                        lutemon.setId(valueId);
                        moveLutemons.add(lutemon);
                        removeCheckBoxes.add(checkBox);
                    }
                }
            }
        }
        if (linearLayoutCheckBox == null) {
            linearLayoutCheckBox = view.findViewById(R.id.linearLayoutTrain);
        }
        // Copilot helped me to fix the bug where checkboxes didn't get removed and the fragment crashed //
        // I checked if the LinearLayout is not null before removing checkboxes //
        if (linearLayoutCheckBox != null) {
            for (CheckBox checkBox : removeCheckBoxes) {
                linearLayoutCheckBox.removeView(checkBox);
                checkBoxList.remove(checkBox);
            }

        }
        storage.saveLutemons(getContext());

        Intent intent = null;
        if (valueId == 2) {
            intent = new Intent(getActivity(), TrainLutemons.class);
            Log.d("works", "wroks");
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
        lutemonWhereaboutsRefresher();
    }

    private void lutemonWhereaboutsRefresher() {
        lutemons = storage.getLutemons();
        ArrayList<Lutemon> lutemons_here = new ArrayList<>();
        for (Lutemon lutemon : lutemons) {
            if (lutemon.getId() == 2) {
                lutemons_here.add(lutemon);
            }
        }
        linearLayoutCheckBox.removeAllViews();
        checkBoxList.clear();

        for (Lutemon lutemon : lutemons_here) {
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(lutemon.getName()+" ("+lutemon.getColor()+")");
            checkBox.setId(View.generateViewId());
            linearLayoutCheckBox.addView(checkBox);
            checkBoxList.add(checkBox);
        }


    }


}

