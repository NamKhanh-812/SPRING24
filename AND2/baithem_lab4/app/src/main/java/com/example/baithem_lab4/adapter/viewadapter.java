package com.example.baithem_lab4.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.baithem_lab4.frg.frag1;
import com.example.baithem_lab4.frg.frag2;

public class viewadapter extends FragmentStateAdapter {
    public viewadapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new frag1();
            case 1:
                return new frag2();
        }
        return new frag1();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
