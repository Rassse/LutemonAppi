package com.example.lutemonappi.fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lutemonappi.Lutemon;
import com.example.lutemonappi.R;
import com.example.lutemonappi.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FightFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FightFragment extends Fragment {
    private List<CheckBox> checkBoxList = new ArrayList<>();
    private Storage storage;
    private CheckBox checkBoxWhite3, checkBoxGreen3, checkBoxPink3, checkBoxOrange3, checkBoxBlack3;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FightFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FightFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FightFragment newInstance(String param1, String param2) {
        FightFragment fragment = new FightFragment();
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
        View view = inflater.inflate(R.layout.fragment_fight, container, false);


        Button buttonToFight = view.findViewById(R.id.buttonToFight);
        buttonToFight.setOnClickListener((View v) -> {
            toFightArea(v);
        });
        storage = Storage.getInstance();
        ArrayList<Lutemon> lutemons = storage.getLutemons();
        ArrayList<Lutemon> lutemonsInFight = new ArrayList<>();
        for (Lutemon lutemon : lutemons) {
            if (lutemon.getId() == 3) {
                lutemonsInFight.add(lutemon);
            }
        }
        RadioGroup radioGroup = view.findViewById(R.id.rgFight);



        for (Lutemon lutemon : lutemonsInFight) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(lutemon.getName() + " ("+lutemon.getColor()+")");
            // COpilot showed be there is a genereateViewId() method to generate unique ids so I can access them easier //
            radioButton.setId(View.generateViewId());
            // I learned here to addView to radioGroup //
            // https://stackoverflow.com/questions/11398103/how-to-use-addview-to-add-view-to-layout //
            radioGroup.addView(radioButton);

        }




        /*
        if (lutemons.size() > 0) {
            checkBoxWhite3.setText(lutemons.get(0).getName() + " (" + lutemons.get(0).getColor() + ")");
        } else  {
            checkBoxWhite3.setVisibility(View.GONE);
        }
        if (lutemons.size() > 1) {
            checkBoxGreen3.setText(lutemons.get(1).getName() + " (" + lutemons.get(1).getColor() + ")");
        } else  {
            checkBoxGreen3.setVisibility(View.GONE);
        }
        if (lutemons.size() > 2) {
            checkBoxPink3.setText(lutemons.get(2).getName()+" ("+lutemons.get(2).getColor()+")");
        } else {
            checkBoxPink3.setVisibility(View.GONE);
        }
        if (lutemons.size() > 3) {
            checkBoxOrange3.setText(lutemons.get(3).getName()+" ("+lutemons.get(3).getColor()+")");
        }
        else {
            checkBoxOrange3.setVisibility(View.GONE);
        }
        if (lutemons.size() > 4) {
            checkBoxBlack3.setText(lutemons.get(4).getName()+" ("+lutemons.get(4).getColor()+")");
        }
        else {
            checkBoxBlack3.setVisibility(View.GONE);
        }
        */
        return view;
    }


    public void toFightArea(View view) {
        System.out.println("Taisteluun");

        RadioGroup radioGroup = view.findViewById(R.id.constraintLayoutId);
        int selectedLutemon = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = view.findViewById(selectedLutemon);
        String name = radioButton.getText().toString();
        for (Lutemon lutemon : storage.getLutemons()) {
            if (name.contains(lutemon.getName())) {
                lutemon.setId(3);

            }
        }

    }
    // https://stackoverflow.com/questions/11326155/fragment-onresume-onpause-is-not-called-on-backstack //
    @Override
    public void onResume() {
        super.onResume();
    }
}