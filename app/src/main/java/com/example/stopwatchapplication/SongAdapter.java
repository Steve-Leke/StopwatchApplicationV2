package com.example.stopwatchapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.annotations.NotNull;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.stopwatchapplication.DiscoverFragment.btnPlaying;
import static com.example.stopwatchapplication.DiscoverFragment.songPos;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.MyViewHolder> {
    private ArrayList<Song> mDataset;
     DiscoverFragment musicFragment;
     MediaPlayer songPlayer;
     public int newSong;
     public int totalTime;






    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    //something wrong with the implements
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        public TextView songTitle;
        public ImageButton playBtn;
        public TextView timeLabel;
        public int currentTime;
        public MyViewHolder(final View itemView) {
            super(itemView);
            songTitle = itemView.findViewById(R.id.songTitle);
            timeLabel = itemView.findViewById(R.id.remainingTimeLabel);
            playBtn = itemView.findViewById(R.id.playBtn);
            currentTime = 0;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SongAdapter(DiscoverFragment musicFragment, ArrayList<Song> myDataset, MediaPlayer songPlayer) {
        mDataset = myDataset;
        this.musicFragment = musicFragment;
        this.songPlayer = songPlayer;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SongAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                       int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.music_options_recycler_cell, viewGroup, false);
        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final TextView artistName = musicFragment.getView().findViewById(R.id.artistName);
        final ImageView artistImage = musicFragment.getView().findViewById(R.id.artistImage);
        holder.timeLabel.setText(mDataset.get(position).getSongLength());
        holder.songTitle.setText(mDataset.get(position).getSongTitle());
        // The problem is that you have multiple threads going to one handler, that's why all the songs time labels are getting changed.
        // You need to maintain a 1 thread to one handler ratio. This means you need to kill all previous threads when a new song is clicked.
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(@NotNull Message msg) {
                int currentPosition = msg.what;

                // Update Labels.
                String remainingTime = createTimeLabel(totalTime-currentPosition);
                holder.timeLabel.setText("- " + remainingTime);
            }
        };
        holder.playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                artistName.setText(mDataset.get(position).getArtistName());
                Picasso.get().load(mDataset.get(position).getArtistPicture()).into(artistImage);
                if (songPos == position) {
                    newSong = 0;
                } else {
                    newSong = 1;
                }
                songPos = position;
                try {
                    if (!songPlayer.isPlaying()) {
//                      startService();
                        if (newSong == 0) {
                            songPlayer.seekTo(holder.currentTime);
                            songPlayer.start();
                            btnPlaying = 0;
                            holder.playBtn.setImageResource(R.drawable.pause);
                            songPlayer.setLooping(true);
                        } else if (newSong == 1) {
                            new Thread(new  Runnable() {
                                @Override
                                public void run() {
                                    while (songPlayer != null) {
                                        try {
                                            Message msg = new Message();
                                            msg.what = songPlayer.getCurrentPosition();
                                            handler.sendMessage(msg);
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {}
                                    }
                                }
                            }).start();
                            handler.removeCallbacksAndMessages(null);
                            songPlayer.reset();
                            songPlayer.setDataSource(mDataset.get(position).getSongUrl());
                            songPlayer.prepare();
                            songPlayer.start();
                            totalTime = songPlayer.getDuration();
                            btnPlaying = 0;
                            holder.playBtn.setImageResource(R.drawable.pause);
                        }
                    } else {
                        btnPlaying = 1;
                        songPlayer.pause();
                        holder.currentTime = songPlayer.getCurrentPosition();
                        holder.playBtn.setImageResource(R.drawable.ic_play);
//                        stopService();
                }
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                Picasso.get().load((mDataset.get(position).getSongUrl()).into(artistImage);
//                if (!mp.isPlaying()) {
//                    mp.start();
//                    holder.playBtn.setImageResource(R.drawable.pause);
//                } else {
//                    mp.pause();
//                    holder.playBtn.setImageResource(R.drawable.ic_play);
//                }

            }
        });

        if (position == songPos) {
            if (btnPlaying == 0) {
                holder.playBtn.setImageResource(R.drawable.pause);

            } else {
                holder.playBtn.setImageResource(R.drawable.ic_play);
            }

        }
//         final Handler handler = new Handler() {
//            @Override
//            public void handleMessage(@NotNull Message msg) {
//                int currentPosition = msg.what;
//
//                // Update Labels.
//                String remainingTime = createTimeLabel(totalTime-currentPosition);
//                holder.timeLabel.setText("- " + remainingTime);
//            }
//        };

//            new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (songPlayer != null) {
//                    try {
//                        Message msg = new Message();
//                        msg.what = songPlayer.getCurrentPosition();
//                        handler.sendMessage(msg);
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {}
//                }
//            }
//        }).start();

    }




    public String createTimeLabel(int time) {
        String timeLabel = "";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;
        timeLabel = min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (mDataset == null) {
            return 0;
        }
        return mDataset.size();
    }

    public void startService() {
        musicFragment.getActivity().startService(new Intent(musicFragment.getActivity(), MusicService.class));
    }

    // Method to stop the service
    public void stopService() {
        musicFragment.getActivity().stopService(new Intent(musicFragment.getActivity(), MusicService.class));
    }
}
