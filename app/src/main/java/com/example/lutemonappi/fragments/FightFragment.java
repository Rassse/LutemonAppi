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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lutemonappi.FightActivity;
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
    private LinearLayout linearLayout;
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
        lutemons = storage.getLutemons();
        ArrayList<Lutemon> lutemons_in_fight = new ArrayList<>();
        for (Lutemon lutemon : lutemons) {
            if (lutemon.getId() == 3) {
                lutemons_in_fight.add(lutemon);
            }
        }
        linearLayout = view.findViewById(R.id.linearLayoutFighting);
        int i = 0;
        for (Lutemon lutemon : lutemons_in_fight) {
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(lutemon.getName() + " ("+lutemon.getColor()+")");
            // Copilot helped me to add new integer and tag in checkBox to check for right Lutemon //
            // With int i, I can create unique ids for every Lutemon //
            checkBox.setId(i);
            checkBox.setTag(lutemon);
            linearLayout.addView(checkBox);
            checkBoxList.add(checkBox);
            i++;
        }

        return view;
    }


    public void toFightArea(View view) {
        System.out.println("Taisteluun");
        // Copilot helped me fix a bug where rgFight name was incorrectly clFight, which is constraintlayoutId, originally //
        ArrayList<Lutemon> lutemons_to_fight = new ArrayList<>();
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isChecked()) {
                // Here I learned to use getTag and setTag methods //
                // https://stackoverflow.com/questions/5291726/what-is-the-main-purpose-of-settag-gettag-methods-of-view //
                Lutemon lutemon = (Lutemon) checkBox.getTag();
                if (!lutemons_to_fight.contains(lutemon)) {
                    Log.d("Toimii lisäys", "Niin kuin pitää");
                    lutemons_to_fight.add(lutemon);
                }
            }
        }
        // print lutemons to check it works //
        for (Lutemon lutemon : lutemons_to_fight) {
            Log.d("Lutemon", "Nimi: " + lutemon.getName() + ", väri: " + lutemon.getColor());
        }

        if (lutemons_to_fight.size() == 2) {
            Log.d("On kaksi Lutemonia", "kyllä");
            for (Lutemon lutemon : lutemons_to_fight) {
                lutemon.setId(5);
            }
            // Copilot helped me to save lutemons in this place so they are properly used from the file //
            storage.saveLutemons(getContext());
            Intent intent = new Intent(getActivity(), FightActivity.class);
            startActivity(intent);
        }
        else {
            TextView textView = new TextView(getContext());
            textView.setText("Valitse kaksi Lutemonia niin pääset Elämäsi taisteluun!!!");
        }

    }
    // https://stackoverflow.com/questions/11326155/fragment-onresume-onpause-is-not-called-on-backstack //
    @Override
    public void onResume() {
        super.onResume();
        lutemonStorageRefresher();
    }

    private void lutemonStorageRefresher() {
        lutemons = storage.getLutemons();
        ArrayList<Lutemon> lutemons_here = new ArrayList<>();
        for (Lutemon lutemon : lutemons) {
            // Copilot helped me fix a bug where fightfragment crashed by checking if lutemon is null //
            // in addition to the id //
            if (lutemon.getId() == 3) {
                lutemons_here.add(lutemon);
            }
        }
        linearLayout.removeAllViews();
        checkBoxList.clear();

        for (Lutemon lutemon : lutemons_here) {
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(lutemon.getName()+" ("+lutemon.getColor()+")");
            checkBox.setId(View.generateViewId());
            checkBox.setTag(lutemon);
            linearLayout.addView(checkBox);
            checkBoxList.add(checkBox);
        }
    }
}