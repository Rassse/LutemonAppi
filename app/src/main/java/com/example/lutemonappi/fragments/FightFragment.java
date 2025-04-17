package com.example.lutemonappi.fragments;

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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FightFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FightFragment extends Fragment {

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

        storage = Storage.getInstance();
        ArrayList<Lutemon> lutemons = storage.getLutemons();
        ArrayList<Lutemon> lutemonsInFight = new ArrayList<>();
        for (Lutemon lutemon : lutemons) {
            if (lutemon.getId() == 3) {
                lutemonsInFight.add(lutemon);
            }
        }
        checkBoxWhite3 = view.findViewById(R.id.checkBoxWhite3);
        checkBoxGreen3 = view.findViewById(R.id.checkBoxGreen3);
        checkBoxPink3= view.findViewById(R.id.checkBoxPink3);
        checkBoxOrange3 = view.findViewById(R.id.checkBoxOrange3);
        checkBoxBlack3 = view.findViewById(R.id.checkBoxBlack3);
        Button buttonToFight = view.findViewById(R.id.buttonToFight);
        buttonToFight.setOnClickListener((View v) -> {
            toFightArea(v);
        });

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

        return view;
    }

    public void toFightArea(View view) {
        System.out.println("Taisteluun");
    }
    // https://stackoverflow.com/questions/11326155/fragment-onresume-onpause-is-not-called-on-backstack //
    @Override
    public void onResume() {
        super.onResume();
    }
}