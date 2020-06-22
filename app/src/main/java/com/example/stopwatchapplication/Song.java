package com.example.stopwatchapplication;

public class Song {
    String songTitle;
    String artistName;
    String artistPicture;
    String songUrl;
    String songLength;
//    MediaPlayer songPlayer;

    public Song () {

    }

    public Song (String songTitle, String artistName, String artistPicture, String songUrl, String songLength) {
        this.songTitle = songTitle;
        this.artistName = artistName;
        this.artistPicture = artistPicture;
        this.songUrl = songUrl;
        this.songLength = songLength;
//        songPlayer = MediaPlayer.create(this, )
////        songPlayer = MediaPlayer.create

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

    public String getSongLength() {
        return songLength;
    }

    public void setSongLength(String songLength) {this.songLength = songLength;
    }
}
