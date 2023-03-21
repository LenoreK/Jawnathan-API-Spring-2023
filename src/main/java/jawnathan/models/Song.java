package jawnathan.models;

public class Song {
    private int songId;
    private String name;
    private String playSongUrl;
    private String photoUrl;
    private String buySongUrl;
    private int albumId;
    private int groupId;

    public Song(int songId, String name, String playSongUrl, String photoUrl, String buySongUrl, int albumId, int groupId) {
        this.songId = songId;
        this.name = name;
        this.playSongUrl = playSongUrl;
        this.photoUrl = photoUrl;
        this.buySongUrl = buySongUrl;
        this.albumId = albumId;
        this.groupId = groupId;
    }

    public Song() {
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaySongUrl() {
        return playSongUrl;
    }

    public void setPlaySongUrl(String playSongUrl) {
        this.playSongUrl = playSongUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getBuySongUrl() {
        return buySongUrl;
    }

    public void setBuySongUrl(String buySongUrl) {
        this.buySongUrl = buySongUrl;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
