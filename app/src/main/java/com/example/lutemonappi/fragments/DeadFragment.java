package com.example.lutemonappi.fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lutemonappi.ActivityNavigator;
import com.example.lutemonappi.Lutemon;
import com.example.lutemonappi.R;
import com.example.lutemonappi.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeadFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    private LinearLayout linearLayoutCheckBox;
    private Storage storage;

    private TextView textView;
    private List<CheckBox> checkBoxList = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DeadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeadFragment newInstance(String param1, String param2) {
        DeadFragment fragment = new DeadFragment();
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
        View view = inflater.inflate(R.layout.fragment_dead, container, false);

        storage = Storage.getInstance();
        lutemons = storage.getLutemons();
        Button resetLutemons = view.findViewById(R.id.buttonDeadRevive);
        resetLutemons.setOnClickListener((View v) -> {
            resetLutemon(v);
        });

        ArrayList<Lutemon> lutemons_here = new ArrayList<>();
        linearLayoutCheckBox = view.findViewById(R.id.linearLayoutDead);
        for (Lutemon lutemon : lutemons) {
            if (lutemon.getId() == 4) {
                lutemons_here.add(lutemon);
            }
        }

        if (lutemons_here.isEmpty()) {
            TextView textView = view.findViewById(R.id.textViewDead);
            textView.setText("Ei kuolleita Lutemoneja.");
            textView.setTextSize(24);
            // I learned here to create bold text //
            // https://stackoverflow.com/questions/17655103/how-to-set-typeface-for-textview-programmatically //
            textView.setTypeface(null, Typeface.BOLD);
        }
        else {
            for (Lutemon lutemon : lutemons_here) {
                CheckBox checkBox = new CheckBox(getContext());
                checkBox.setText(lutemon.getName() + " (" + lutemon.getColor() + ")");
                // Copilot helped me to generate unique ID I can access later on //
                checkBox.setId(View.generateViewId());
                linearLayoutCheckBox.addView(checkBox);
                checkBoxList.add(checkBox);
            }
        }

        return view;
    }

    // https://stackoverflow.com/questions/11326155/fragment-onresume-onpause-is-not-called-on-backstack //
    @Override
    public void onResume() {
        super.onResume();
    }

    public void resetLutemon(View view) {
        // COpilot helped me to create a new arraylist so that I can remove checkboxes and the fragment doesn't crash //
        ArrayList<CheckBox> removeCheckBoxes = new ArrayList<>();
        ArrayList<Lutemon> moveLutemons = new ArrayList<>();
        int losses = 1;
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isChecked()) {
                for (Lutemon lutemon : storage.getLutemons()) {
                    if (checkBox.getText().toString().contains(lutemon.getName())) {
                        lutemon.setId(1);
                        lutemon.setLosses(losses);
                        lutemon.setHealth(lutemon.getMaxHealth());
                        moveLutemons.add(lutemon);
                        removeCheckBoxes.add(checkBox);
                        losses++;
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

        Intent intent = new Intent(getActivity(), ActivityNavigator.class);
        startActivity(intent);

    }

}