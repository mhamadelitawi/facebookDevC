package com.example.facebook.model;

public class Access {

    private Integer id;
    private User user;
    private PlayList playlistId;
    private AccessType type;


    public Access() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PlayList getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(PlayList playlistId) {
        this.playlistId = playlistId;
    }

    public AccessType getType() {
        return type;
    }

    public void setType(AccessType type) {
        this.type = type;
    }
}
