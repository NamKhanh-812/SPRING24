package com.example.baithem_lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.baithem_lab4.adapter.viewadapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class tablayout extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    viewadapter viewadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        tabLayout = findViewById(R.id.tablayout);
        viewPager2 = findViewById(R.id.viewpage2);
        viewadapter = new viewadapter(this);
        viewPager2.setAdapter(viewadapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setIcon(R.drawable.video);
                        tab.setText("Phim");
                        break;
                    case 1:
                        tab.setIcon(R.drawable.video);
                        tab.setText("Phim siêu nhân");
                        break;
                }
            }
        }).attach();
    }
}