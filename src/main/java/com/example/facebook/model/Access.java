package com.example.facebook.model;

public class Access {

    private Integer id;
    private Integer userId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
