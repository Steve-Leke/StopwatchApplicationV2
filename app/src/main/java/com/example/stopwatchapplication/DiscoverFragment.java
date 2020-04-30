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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stopwatchapplication.R;
import com.github.florent37.expansionpanel.ExpansionLayout;
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


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
    RecyclerView recyclerView;
    RecyclerAdapter adapter;


    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_discover, container, false);

        playBtn = (ImageButton) v.findViewById(R.id.playBtn);
        remainingTimeLabel = (TextView) v.findViewById(R.id.remainingTimeLabel);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        adapter = new RecyclerAdapter();
//        recyclerView.setAdapter(adapter);


        // Media Player
        mp = MediaPlayer.create(getActivity(), R.raw.robbery);
        mp.setLooping(false);
        mp.seekTo(0);
//      mp.setVolume(0.5f, 0.5f);
        totalTime = mp.getDuration();



        // Media Player
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                if (!mp.isPlaying()) {
                    mp.start();
                    int color = Color.parseColor("#FF769A"); //The color u want
                    playBtn.setBackgroundResource(R.mipmap.ic_pause);
                    playBtn.setImageResource(R.mipmap.ic_pause);
//                    playBtn.setColorFilter(color);
//                    playBtn.setColorFilter(color);
                } else {
                    mp.pause();
                    playBtn.setBackgroundResource(R.drawable.play_background);
                    playBtn.setImageResource(R.drawable.ic_play);
                }

            }
        });

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

//        //fill with empty objects
//        final List<Object> list = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            list.add(new Object());
//        }
//        adapter.setItems(list);
        return v;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = msg.what;

            // Update Labels.

            String remainingTime = createTimeLabel(totalTime-currentPosition);
            remainingTimeLabel.setText("- " + remainingTime);
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

}
