package com.example.lutemonappi.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioGroup;


import com.example.lutemonappi.Lutemon;
import com.example.lutemonappi.LutemonListAdapter;
import com.example.lutemonappi.R;
import com.example.lutemonappi.RestLutemons;
import com.example.lutemonappi.Storage;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private Storage storage;
    private int id = 0;
    private CheckBox checkBoxWhite, checkBoxGreen, checkBoxPink, checkBoxOrange, checkBoxBlack;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();


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
        ArrayList<Lutemon> lutemons = storage.getLutemons();
        checkBoxWhite = view.findViewById(R.id.checkBoxWhite);
        checkBoxGreen = view.findViewById(R.id.checkBoxGreen);
        checkBoxPink = view.findViewById(R.id.checkBoxPink);
        checkBoxOrange = view.findViewById(R.id.checkBoxOrange);
        checkBoxBlack = view.findViewById(R.id.checkBoxBlack);
        RadioGroup rgLutemonWhereabouts = view.findViewById(R.id.rgLutemonHome);
        int checkId = rgLutemonWhereabouts.getCheckedRadioButtonId();
        if (checkId == R.id.radioButtonHomeHome) {
            id = 1;
        }
        else if (checkId == R.id.radioButtonTrainHome) {
            id = 2;
        }
        else if (checkId == R.id.radioButtonFightHome) {
            id = 3;
        }

        // Copilot helped me figure out that I can check lutemons.size() to retrieve the lutemons color right to the checkBoxes //
        if (lutemons.size() > 0) {
            checkBoxWhite.setText(lutemons.get(0).getName() + " (" + lutemons.get(0).getColor() + ")");
            lutemons.get(0).setId(id);
        } else  {
            checkBoxWhite.setVisibility(View.GONE);
        }
        if (lutemons.size() > 1) {
            checkBoxGreen.setText(lutemons.get(1).getName() + " (" + lutemons.get(1).getColor() + ")");
            lutemons.get(1).setId(id);
        } else  {
            checkBoxGreen.setVisibility(View.GONE);
        }
        if (lutemons.size() > 2) {
            checkBoxPink.setText(lutemons.get(2).getName()+" ("+lutemons.get(2).getColor()+")");
            lutemons.get(2).setId(id);
        } else {
            checkBoxPink.setVisibility(View.GONE);
        }
        if (lutemons.size() > 3) {
            checkBoxOrange.setText(lutemons.get(3).getName()+" ("+lutemons.get(3).getColor()+")");
            lutemons.get(3).setId(id);
        }
        else {
            checkBoxOrange.setVisibility(View.GONE);
        }
        if (lutemons.size() > 4) {
            checkBoxBlack.setText(lutemons.get(4).getName()+" ("+lutemons.get(4).getColor()+")");
            lutemons.get(4).setId(id);
        }
        else {
            checkBoxBlack.setVisibility(View.GONE);
        }


        return view;
    }
    public void moveHome(View view) {
        if (id == 1) {
            Intent intent = new Intent(getActivity(), RestLutemons.class);
            startActivity(intent);
        }
    }
}