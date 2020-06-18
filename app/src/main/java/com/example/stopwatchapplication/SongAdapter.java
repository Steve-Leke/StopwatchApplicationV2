package com.example.stopwatchapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.MyViewHolder> {
    private ArrayList<Song> mDataset;
     Fragment musicFragment;
     MediaPlayer songPlayer;
     int totalTime;



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    //something wrong with the implements
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        public TextView songTitle;
        public ImageButton playBtn;
        public MyViewHolder(final View itemView) {
            super(itemView);
            songTitle = itemView.findViewById(R.id.songTitle);
            playBtn = itemView.findViewById(R.id.playBtn);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SongAdapter(Fragment musicFragment, ArrayList<Song> myDataset, MediaPlayer songPlayer) {
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
        holder.songTitle.setText(mDataset.get(position).getSongTitle());
        holder.playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                artistName.setText(mDataset.get(position).getArtistName());
                Picasso.get().load(mDataset.get(position).getArtistPicture()).into(artistImage);
                try {
                    totalTime = songPlayer.getDuration();
                    if (!songPlayer.isPlaying()) {
//                        startService();
                        songPlayer.reset();
                        songPlayer.setDataSource(mDataset.get(position).getSongUrl());
                        songPlayer.prepare();
                        songPlayer.start();
                        holder.playBtn.setImageResource(R.drawable.pause);
                    } else {
                        songPlayer.pause();
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
