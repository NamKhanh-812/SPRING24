package com.example.lab4_ph43678;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Bai3 extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    viewPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);
        //ánh xạ
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPage2);
        adapter = new viewPageAdapter(this);
        viewPager2.setAdapter(adapter);
        // config tablayout
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Tab1");
                        break;
                    case 1:
                        tab.setText("Tab2");
                        break;
                }
            }
        }).attach();
    }
}