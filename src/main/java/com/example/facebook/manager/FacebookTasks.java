package com.example.facebook.manager;

import com.example.facebook.model.AccessType;
import com.example.facebook.model.PlayList;
import com.example.facebook.model.User;
import com.example.facebook.model.Video;

import java.util.List;

public interface FacebookTasks {



    public User register(String username , String password);

    public PlayList getPlayList(User user);

    public PlayList createPlayList(Integer userId, String title);

    public User signIn(String username , String password);

    public void grantAccess(Integer playListId , Integer user , AccessType type);

}
