package com.example.stopwatchapplication;


import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import android.util.Log;

public class StopWatchAct extends AppCompatActivity {

    ChipNavigationBar bottomNav;
    FragmentManager fragmentManager;
    DiscoverFragment discoverFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        bottomNav = findViewById(R.id.bottom_nav);
        final String TAG = StopWatchAct.class.getSimpleName();

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
