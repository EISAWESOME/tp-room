package com.simonin.clement.tp2room;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.simonin.clement.tp2room.fragment.AddPlaceFragment;
import com.simonin.clement.tp2room.fragment.MapsFragment;
import com.simonin.clement.tp2room.fragment.PlaceListFragment;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, new AddPlaceFragment())
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, new AddPlaceFragment())
                        .commit();
                break;
            case R.id.navigation_dashboard:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, new PlaceListFragment())
                        .commit();
                break;
            case R.id.navigation_maps:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, new MapsFragment())
                        .commit();
                break;
        }
        return true;
    }

}
