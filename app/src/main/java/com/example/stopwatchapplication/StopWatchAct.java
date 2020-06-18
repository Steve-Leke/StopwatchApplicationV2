package com.example.stopwatchapplication;


import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class StopWatchAct extends AppCompatActivity {

    ChipNavigationBar bottomNav;
    FragmentManager fragmentManager;
    DiscoverFragment discoverFragment;
    public static MediaPlayer songPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        bottomNav = findViewById(R.id.bottom_nav);
        final String TAG = StopWatchAct.class.getSimpleName();
        songPlayer = new MediaPlayer();
        songPlayer.setAudioAttributes(
                new AudioAttributes
                        .Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build());

        if (savedInstanceState == null) {
            bottomNav.setItemSelected(R.id.home, true);
            fragmentManager = getSupportFragmentManager();
            HomeFragment homeFragment = new HomeFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, homeFragment)
                    .commit();
        }

//        if (savedInstanceState != null) {
//            //Restore the fragment's instance
//            discoverFragment =  (DiscoverFragment) getSupportFragmentManager().getFragment(savedInstanceState, "DiscoverFragment");
//        }
//
//        protected void onSaveInstanceState(Bundle outState) {
//            super.onSaveInstanceState(outState);
//
//            //Save the fragment's instance
//            getSupportFragmentManager().putFragment(outState, "myFragmentName", mMyFragment);
//        }
//

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch (id) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.timer:
                        fragment = new TimerFragment();
                        break;
                    case R.id.discover:
                        if (discoverFragment == null) {
                            discoverFragment =  new DiscoverFragment();
                            fragment = discoverFragment;
                        } else {
                            fragment = discoverFragment;
                        }
                        break;
                    case R.id.account:
                        fragment = new AccountFragment();
                        break;

                }

                if (fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }  else {
                    Log.e(TAG, "Error in creating fragment");
                }

            }
        });


    }

}
