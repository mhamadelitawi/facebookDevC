package com.example.facebook.manager;

import com.example.facebook.model.PlayList;
import com.example.facebook.model.User;
import com.example.facebook.model.Video;

import java.util.List;

public interface FacebookTasks {



    public User register(String username , String password);

    public PlayList getPlayList(User user);

    public PlayList createPlayList(User user, List<Video> videos);

    public User signIn(String username , String password);

}
