package com.example.stopwatchapplication;

import android.media.Image;

import java.net.URL;

public class Song {
    String songTitle;
    String artistName;
    String artistPicture;
    String songUrl;

    public Song () {

    }

    public Song (String songTitle, String artistName, String artistPicture, String songUrl) {
        this.songTitle = songTitle;
        this.artistName = artistName;
        this.artistPicture = artistPicture;
        this.songUrl = songUrl;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistPicture() {
        return artistPicture;
    }

    public void setArtistPicture(String artistPicture) {
        this.artistPicture = artistPicture;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }
}
