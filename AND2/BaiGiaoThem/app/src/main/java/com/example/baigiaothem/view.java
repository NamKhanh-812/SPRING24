package com.example.baigiaothem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.baigiaothem.Fragment.DienVienNe;
import com.example.baigiaothem.Fragment.PhimNe;

public class view extends FragmentStateAdapter {
    public view(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PhimNe();
            case 1:
                return new DienVienNe();

        }
        return new PhimNe();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
