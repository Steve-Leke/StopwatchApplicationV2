package com.example.stopwatchapplication;

import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {
    ImageButton playBtn;
    TextView remainingTimeLabel;
    MediaPlayer songPlayer;
    int totalTime;
    TextView lucidDreams;
    TextView robbery;
    ArrayList<Song> songList = new ArrayList<>();
    SongAdapter songAdapter;
    DatabaseReference databaseSongs;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            //Restore the fragment's state here

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("onCreateView", "starting...");

//        addItemsToRecyclerViewArrayList();
        // super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_discover, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseSongs = database.getReference().child("songs");
        songPlayer = new MediaPlayer();
        songPlayer.setAudioAttributes(
                new AudioAttributes
                        .Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build());
        songAdapter = new SongAdapter(this,songList, songPlayer);
        databaseSongs.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Song song = dataSnapshot.getValue(Song.class);
                songList.add(song);
                songAdapter.notifyDataSetChanged();
                Log.d("pizza", "onChildAdded: ");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(songAdapter);

        //playBtn = (ImageButton) v.findViewById(R.id.playBtn);
        //remainingTimeLabel = (TextView) v.findViewById(R.id.remainingTimeLabel);
        //lucidDreams = (TextView) v.findViewById(R.id.lucidDreams);
        //robbery = (TextView) v.findViewById(R.id.robbery);


        // Media Player
//        mp = MediaPlayer.create(getActivity(), R.raw.robbery);
//        mp.setLooping(false);
////        mp.seekTo(0);
//        mp.setVolume(0.5f, 0.5f);
//        totalTime = mp.getDuration();

////         Media Player
//         playBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick (View v) {
//                if (!mp.isPlaying()) {
//                    mp.start();
////                    playBtn.setImageResource(R.drawable.pause);
//                } else {
//                    mp.pause();
//                    playBtn.setImageResource(R.drawable.ic_play);
//                }
//
//            }
//        });

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (mp != null) {
//                    try {
//                        Message msg = new Message();
//                        msg.what = mp.getCurrentPosition();
//                        handler.sendMessage(msg);
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {}
//                }
//            }
//        }).start();
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

//    // Function to add items in RecyclerView.
//    public void addItemsToRecyclerViewArrayList()
//    {
//        // Adding items to ArrayList
//        songList = new ArrayList<>();
//        songList.add("Song 1");
//        songList.add("Song 2");
//        songList.add("Song 3");
//        songList.add("Song 4");
//        songList.add("Song 5");
//        songList.add("Song 6");
//        songList.add("Song 7");
//        songList.add("Song 8");
//
//    }
@Override
public void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);

    }

}





