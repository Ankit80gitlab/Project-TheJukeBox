package entity;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;

public class podcast
{
    private int podId;
    private String podName;
    private Date podDate;
    private Time podDuration;
    private String podDesc;
    private String podAuthor;
    private String podURL;
    private int audioId;
    private String audioType;

    public podcast(int podId, String podName, Date podDate, Time podDuration, String podDesc, String podAuthor, String podURL, int audioId, String audioType) {
        this.podId = podId;
        this.podName = podName;
        this.podDate = podDate;
        this.podDuration = podDuration;
        this.podDesc = podDesc;
        this.podAuthor = podAuthor;
        this.podURL = podURL;
        this.audioId = audioId;
        this.audioType = audioType;
    }

    public int getPodId() {
        return podId;
    }

    public void setPodId(int podId) {
        this.podId = podId;
    }

    public String getPodName() {
        return podName;
    }

    public void setPodName(String podName) {
        this.podName = podName;
    }

    public Date getPodDate() {
        return podDate;
    }

    public void setPodDate(Date podDate) {
        this.podDate = podDate;
    }

    public Time getPodDuration() {
        return podDuration;
    }

    public void setPodDuration(Time podDuration) {
        this.podDuration = podDuration;
    }

    public String getPodDesc() {
        return podDesc;
    }

    public void setPodDesc(String podDesc) {
        this.podDesc = podDesc;
    }

    public String getPodAuthor() {
        return podAuthor;
    }

    public void setPodAuthor(String podAuthor) {
        this.podAuthor = podAuthor;
    }

    public String getPodURL() {
        return podURL;
    }

    public void setPodURL(String podURL) {
        this.podURL = podURL;
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
        return "podcast{" +
                "podId=" + podId +
                ", podName='" + podName + '\'' +
                ", podDate=" + podDate +
                ", podDuration=" + podDuration +
                ", podDesc='" + podDesc + '\'' +
                ", podAuthor='" + podAuthor + '\'' +
                ", podURL='" + podURL + '\'' +
                ", audioId=" + audioId +
                ", audioType='" + audioType + '\'' +
                '}';
    }
}
