package com.example.lutemonappi.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
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

import com.example.lutemonappi.ActivityNavigator;
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
    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    private RadioGroup rgLutemonWhereabouts;
    private LinearLayout linearLayoutCheckBox;


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
        rgLutemonWhereabouts = view.findViewById(R.id.rgLutemonHome);
        Button moveButtonsHome = view.findViewById(R.id.moveButtonsHome);
        moveButtonsHome.setOnClickListener((View v) -> {
            moveLutemons(v);
        });
        Button moveNaviHome = view.findViewById(R.id.buttonNaviHome);
        moveNaviHome.setOnClickListener((View v) -> {
            moveNavi(v);
        });
        ArrayList<Lutemon> lutemons_here = new ArrayList<>();
        linearLayoutCheckBox = view.findViewById(R.id.linearLayoutCheckBox);
        for (Lutemon lutemon : lutemons) {
            if (lutemon.getId() == 1) {
                lutemons_here.add(lutemon);
            }
        }

        for (Lutemon lutemon : lutemons_here) {
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(lutemon.getName() + " (" + lutemon.getColor() + ")");
            // Copilot helped me to generate unique ID I can access later on //
            checkBox.setId(View.generateViewId());
            linearLayoutCheckBox.addView(checkBox);
            checkBoxList.add(checkBox);
        }

    }


    public void moveLutemons(View view) {
        int checkId = rgLutemonWhereabouts.getCheckedRadioButtonId();
        if (checkId == R.id.radioButtonHomeHome) {
            valueId = 1;
        } else if (checkId == R.id.radioButtonTrainHome) {
            valueId = 2;
            Log.d("Works", "WOrks");
        } else if (checkId == R.id.radioButtonFightHome) {
            valueId = 3;
        }
        ArrayList<Lutemon> lutemons_moving = new ArrayList<>();
        // I had trouble having the checkboxes visible or deleting them from the view //
        // COpilot helped me to debug and I had to create a new Arraylist //
        ArrayList<CheckBox> removeCheckBoxes = new ArrayList<>();
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isChecked()) {
                for (Lutemon lutemon : storage.getLutemons()) {
                    if (checkBox.getText().toString().contains(lutemon.getName())) {
                        lutemon.setId(valueId);
                        lutemons_moving.add(lutemon);
                        removeCheckBoxes.add(checkBox);
                    }
                }
            }
        }

        if (linearLayoutCheckBox == null) {
            linearLayoutCheckBox = view.findViewById(R.id.linearLayoutCheckBox);
        }
        if (linearLayoutCheckBox != null) {
            for (CheckBox checkBox : removeCheckBoxes) {
                linearLayoutCheckBox.removeView(checkBox);
                checkBoxList.remove(checkBox);
            }

        }
        storage.saveLutemons(getContext());

        Intent intent = null;
        if (valueId == 1) {
            intent = new Intent(getActivity(), RestLutemons.class);
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
            if (lutemon.getId() == 1) {
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

    public void moveNavi(View view) {
        Intent intent = new Intent(getActivity(), ActivityNavigator.class);
        startActivity(intent);
    }
}