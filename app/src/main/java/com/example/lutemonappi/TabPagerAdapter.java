package com.example.lutemonappi;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.lutemonappi.fragments.DeadFragment;
import com.example.lutemonappi.fragments.FightFragment;
import com.example.lutemonappi.fragments.HomeFragment;
import com.example.lutemonappi.fragments.TrainingFragment;

public class TabPagerAdapter extends FragmentStateAdapter {
    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new TrainingFragment();
            case 2:
                return new FightFragment();
            case 3:
                return new DeadFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
