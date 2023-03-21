package jawnathan.models;

public class Video {
    private int videoId;
    private String url;
    private String name;

    public Video(int videoId, String url, String name) {
        this.videoId = videoId;
        this.url = url;
        this.name = name;
    }

    public Video() {
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}