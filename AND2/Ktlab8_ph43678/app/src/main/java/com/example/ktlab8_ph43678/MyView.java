package com.example.ktlab8_ph43678;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyView extends FragmentStateAdapter {
    public MyView(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FrgMonHoc();
            case 1:
                return new FrgThongTin();
        }
        return new FrgMonHoc();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
