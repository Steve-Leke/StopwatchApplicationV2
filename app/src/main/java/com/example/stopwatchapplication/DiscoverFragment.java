package com.example.stopwatchapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stopwatchapplication.R;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {
    ImageButton playBtn;
    TextView remainingTimeLabel;
    MediaPlayer mp;
    int totalTime;
    TextView lucidDreams;
    TextView robbery;

    ArrayList<Object> songList;

    RecyclerAdapter adapter;

    public DiscoverFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("onCreateView", "starting...");

        addItemsToRecyclerViewArrayList();
        // super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_discover, container, false);

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


        RecyclerAdapter adapter = new RecyclerAdapter(songList);
        recyclerView.setAdapter(adapter);


        //playBtn = (ImageButton) v.findViewById(R.id.playBtn);
        //remainingTimeLabel = (TextView) v.findViewById(R.id.remainingTimeLabel);
        //lucidDreams = (TextView) v.findViewById(R.id.lucidDreams);
        //robbery = (TextView) v.findViewById(R.id.robbery);


        // Media Player
        mp = MediaPlayer.create(getActivity(), R.raw.robbery);
        mp.setLooping(false);
        mp.seekTo(0);
//        mp.setVolume(0.5f, 0.5f);
        totalTime = mp.getDuration();

        // Media Player
        //        playBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick (View v) {
//                if (!mp.isPlaying()) {
//                    mp.start();
//                    playBtn.setImageResource(R.drawable.pause);
//                } else {
//                    mp.pause();
//                    playBtn.setImageResource(R.drawable.ic_play);
//                }
//
//            }
//        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mp != null) {
                    try {
                        Message msg = new Message();
                        msg.what = mp.getCurrentPosition();
                        handler.sendMessage(msg);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                }
            }
        }).start();
        Typeface MLight = Typeface.createFromAsset(getContext().getAssets(), "fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getContext().getAssets(), "fonts/MMedium.ttf");
        Typeface MRegular = Typeface.createFromAsset(getContext().getAssets(), "fonts/MRegular.ttf");
//        robbery.setTypeface(MMedium);
//        lucidDreams.setTypeface(MMedium);
        return v;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NotNull Message msg) {
            int currentPosition = msg.what;

            // Update Labels.

            String remainingTime = createTimeLabel(totalTime-currentPosition);
            //    remainingTimeLabel.setText("- " + remainingTime);
        }
    };

    public String createTimeLabel(int time) {
        String timeLabel = "";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;
        timeLabel = min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }

    // Function to add items in RecyclerView.
    public void addItemsToRecyclerViewArrayList()
    {
        // Adding items to ArrayList
        songList = new ArrayList<>();
        songList.add("Song 1");
        songList.add("Song 2");
        songList.add("Song 3");
        songList.add("Song 4");
        songList.add("Song 5");
        songList.add("Song 6");
        songList.add("Song 7");
        songList.add("Song 8");

    }
}





