package com.example.facebook.database;

import com.example.facebook.model.PlayList;
import com.example.facebook.model.User;
import com.example.facebook.model.Video;

import java.util.List;

public interface FacebookTasks {


    public User signIn(String username , String password);

    public PlayList getPlayList(User user);

    public User register(User user);

    public PlayList createPlayList(User user, List<Video> videos);


}
