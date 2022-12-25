package entity;

public class audio
{
    private int audioId;
    private int songId;
    private int podcastId;
    private String audioType;

    public audio(int audioId, int songId, int podcastId, String audioType) {
        this.audioId = audioId;
        this.songId = songId;
        this.podcastId = podcastId;
        this.audioType = audioType;
    }

    public int getAudioId() {
        return audioId;
    }

    public void setAudioId(int audioId) {
        this.audioId = audioId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public int getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(int podcastId) {
        this.podcastId = podcastId;
    }

    public String getAudioType() {
        return audioType;
    }

    public void setAudioType(String audioType) {
        this.audioType = audioType;
    }

    @Override
    public String toString() {
        return "audio{" +
                "audioId=" + audioId +
                ", songId=" + songId +
                ", podcastId=" + podcastId +
                ", audioType='" + audioType + '\'' +
                '}';
    }
}
