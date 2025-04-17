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
import com.example.lutemonappi.Storage;
import com.example.lutemonappi.TrainLutemons;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrainingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrainingFragment extends Fragment {

    private Storage storage;
    private CheckBox checkBoxWhite2, checkBoxGreen2, checkBoxPink2, checkBoxOrange2, checkBoxBlack2;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

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
        checkBoxWhite2 = view.findViewById(R.id.checkBoxWhite2);
        checkBoxGreen2 = view.findViewById(R.id.checkBoxGreen2);
        checkBoxPink2= view.findViewById(R.id.checkBoxPink2);
        checkBoxOrange2 = view.findViewById(R.id.checkBoxOrange2);
        checkBoxBlack2 = view.findViewById(R.id.checkBoxBlack2);
        Button moveButtonsTrain = view.findViewById(R.id.moveButtonsTrain);
        moveButtonsTrain.setOnClickListener((View v) -> {
            switchToTrainLutemons(v);
        });
        RadioGroup rgLutemonWhereAbouts = view.findViewById(R.id.rgTraining);
        int checkId = rgLutemonWhereAbouts.getCheckedRadioButtonId();
        // Copilot helped me to check Id through iteration rather than long if else clauses //
        ArrayList<Lutemon> lutemonsInTraining = new ArrayList<>();
        for (Lutemon lutemon : lutemons) {
            if (lutemon.getId() == 2) {
                lutemonsInTraining.add(lutemon);
            }
        }

        // Copilot helped me figure out that I can check lutemons.size() to retrieve the lutemons color right to the checkBoxes //
        if (lutemons.size() > 0) {
            checkBoxWhite2.setText(lutemons.get(0).getName() + " (" + lutemons.get(0).getColor() + ")");
        } else  {
            checkBoxWhite2.setVisibility(View.GONE);
        }
        if (lutemons.size() > 1) {
            checkBoxGreen2.setText(lutemons.get(1).getName() + " (" + lutemons.get(1).getColor() + ")");
        } else  {
            checkBoxGreen2.setVisibility(View.GONE);
        }
        if (lutemons.size() > 2) {
            checkBoxPink2.setText(lutemons.get(2).getName()+" ("+lutemons.get(2).getColor()+")");
        } else {
            checkBoxPink2.setVisibility(View.GONE);
        }
        if (lutemons.size() > 3) {
            checkBoxOrange2.setText(lutemons.get(3).getName()+" ("+lutemons.get(3).getColor()+")");
        }
        else {
            checkBoxOrange2.setVisibility(View.GONE);
        }
        if (lutemons.size() > 4) {
            checkBoxBlack2.setText(lutemons.get(4).getName()+" ("+lutemons.get(4).getColor()+")");
        }
        else {
            checkBoxBlack2.setVisibility(View.GONE);
        }

        return view;
    }

    public void switchToTrainLutemons(View view) {
        ArrayList<Lutemon> lutemonsInTraining = new ArrayList<>();
        for (Lutemon lutemon : storage.getLutemons()) {
            if (lutemon.getId() == 2) {
                lutemonsInTraining.add(lutemon);
            }
        }
        if (!lutemonsInTraining.isEmpty()) {
            storage.saveLutemons(getContext());
            Intent intent = new Intent(getActivity(), TrainLutemons.class);
            startActivity(intent);
        }
    }

    // https://stackoverflow.com/questions/11326155/fragment-onresume-onpause-is-not-called-on-backstack //
    @Override
    public void onResume() {
        super.onResume();
    }
}

