package com.example.lab4_ph43678;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class viewPageAdapter extends FragmentStateAdapter {

    public viewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new frgBai1a();
            case 1:
                return new frgBai1b();

        }
        return new frgBai1a();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
