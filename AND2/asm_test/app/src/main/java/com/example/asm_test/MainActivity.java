package com.example.asm_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav);

        //gán tool bar lên action bar
        setSupportActionBar(toolbar);

        //set drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setItemIconTintList(null);//set màu icon về nguyên bản

        //click item
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.trangchu) {
                    frg_trangchu frg_trangchu = new frg_trangchu();
                    replaceFrg(frg_trangchu);
                } else if (item.getItemId() == R.id.caidat) {
                    frg_caidat frg_caidat = new frg_caidat();
                    replaceFrg(frg_caidat);
                }
                return true;
            }
        });
    }

    //
    public void replaceFrg(Fragment frg) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmnav, frg).commit();
    }
}