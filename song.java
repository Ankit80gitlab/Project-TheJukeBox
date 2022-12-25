package entity;

import java.sql.Time;

public class song implements Comparable<song>
{
    private int songId;
    private String songName;
    private String genre;
    private Time songDuration;
    private String songAlbum;
    private String songArtist;
    private String songURL;
    private int audioId;
    private String audioType;

    public song(int songId, String songName, String genre, Time songDuration, String songAlbum, String songArtist, String songURL, int audioId, String audioType) {
        this.songId = songId;
        this.songName = songName;
        this.genre = genre;
        this.songDuration = songDuration;
        this.songAlbum = songAlbum;
        this.songArtist = songArtist;
        this.songURL = songURL;
        this.audioId = audioId;
        this.audioType = audioType;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Time getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(Time songDuration) {
        this.songDuration = songDuration;
    }

    public String getSongAlbum() {
        return songAlbum;
    }

    public void setSongAlbum(String songAlbum) {
        this.songAlbum = songAlbum;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongURL() {
        return songURL;
    }

    public void setSongURL(String songURL) {
        this.songURL = songURL;
    }

    public int getAudioId() {
        return audioId;
    }

    public void setAudioId(int audioId) {
        this.audioId = audioId;
    }

    public String getAudioType() {
        return audioType;
    }

    public void setAudioType(String audioType) {
        this.audioType = audioType;
    }

    @Override
    public String toString() {
        return "song{" +
                "songId=" + songId +
                ", songName='" + songName + '\'' +
                ", genre='" + genre + '\'' +
                ", songDuration=" + songDuration +
                ", songAlbum='" + songAlbum + '\'' +
                ", songArtist='" + songArtist + '\'' +
                ", songURL='" + songURL + '\'' +
                ", audioId=" + audioId +
                ", audioType='" + audioType + '\'' +
                '}';
    }


    @Override
    public int compareTo(song o) {
        return 0;
    }
}
