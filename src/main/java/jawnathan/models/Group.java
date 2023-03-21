package jawnathan.models;

public class Group {
    private int groupId;
    private String groupName;
    private String genre;
    private String photoUrl;
    private String websiteUrl;

    public Group(int groupId, String groupName, String genre, String photoUrl, String websiteUrl) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.genre = genre;
        this.photoUrl = photoUrl;
        this.websiteUrl = websiteUrl;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }
}