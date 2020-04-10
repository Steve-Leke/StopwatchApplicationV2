package com.example.stopwatchapplication;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import com.example.stopwatchapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    Button btnstart, btnstop;
    ImageView icanchor;
    Animation roundingalone;
    Chronometer timerHere;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        btnstart = v.findViewById(R.id.btnstart);
        icanchor = v.findViewById(R.id.icanchor);
        btnstop = v.findViewById(R.id.btnstop);
        timerHere = v.findViewById(R.id.timerHere);

        //create optional animation
        btnstop.setAlpha(0);

        //load animations
        roundingalone = AnimationUtils.loadAnimation(getContext(), R.anim.roundingalone);

        // import font
        Typeface MLight = Typeface.createFromAsset(getActivity().getAssets(), "fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getActivity().getAssets(), "fonts/MMedium.ttf");
        Typeface MRegular = Typeface.createFromAsset(getActivity().getAssets(), "fonts/MRegular.ttf");

        // customize font
        btnstart.setTypeface(MMedium);
        btnstop.setTypeface(MMedium);

        btnstart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                icanchor.startAnimation(roundingalone);
                btnstop.animate().alpha(1).translationY(-40).setDuration(300).start();
                btnstart.animate().alpha(0).setDuration(300).start();
                //start time
                timerHere.setBase(SystemClock.elapsedRealtime());
                timerHere.start();
            }

        });

        btnstop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                icanchor.getAnimation().cancel();
                icanchor.clearAnimation();
                btnstart.animate().alpha(1).translationY(0).setDuration(300).start();
                btnstop.animate().alpha(0).setDuration(300).start();
                timerHere.setBase(SystemClock.elapsedRealtime());
                timerHere.stop();
            }

        });


        return v;
    }

}
