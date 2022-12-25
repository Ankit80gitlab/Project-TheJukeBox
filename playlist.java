package entity;

import java.sql.Time;
import java.util.Timer;

public class playlist
{
    private int iD;
    private String aName;
    private Time aDuration;
    private String aArtist;

    public playlist(int iD, String aName, Time aDuration, String aArtist) {
        this.iD = iD;
        this.aName = aName;
        this.aDuration = aDuration;
        this.aArtist = aArtist;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public Time getaDuration() {
        return aDuration;
    }

    public void setaDuration(Time aDuration) {
        this.aDuration = aDuration;
    }

    public String getaArtist() {
        return aArtist;
    }

    public void setaArtist(String aArtist) {
        this.aArtist = aArtist;
    }

    @Override
    public String toString() {
        return "playlist{" +
                "iD=" + iD +
                ", aName='" + aName + '\'' +
                ", aDuration=" + aDuration +
                ", aArtist='" + aArtist + '\'' +
                '}';
    }
}
