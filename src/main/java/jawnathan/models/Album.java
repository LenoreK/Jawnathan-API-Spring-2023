package jawnathan.models;

public class Album {
    private int albumId;
    private String name;
    private int releaseYear;
    private String photoUrl;
    private String albumUrl;

    public Album(int albumId, String name, int releaseYear, String photoUrl, String albumUrl) {
        this.albumId = albumId;
        this.name = name;
        this.releaseYear = releaseYear;
        this.photoUrl = photoUrl;
        this.albumUrl = albumUrl;
    }

    public Album() {
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getAlbumUrl() {
        return albumUrl;
    }

    public void setAlbumUrl(String albumUrl) {
        this.albumUrl = albumUrl;
    }
}
