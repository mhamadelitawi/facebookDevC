package com.example.facebook.manager;

import com.example.facebook.model.PlayList;
import com.example.facebook.model.User;
import com.example.facebook.model.Video;

import java.util.List;

import static com.example.facebook.Utils.Tools.hashToSHA1;

public class ControllerManager implements FacebookTasks {


    public DatabaseManager databaseManager = null ;

    public ControllerManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public User register(String username , String password)
    {
        password = hashToSHA1(password);
        if( databaseManager.userManager.createUser(username , password) == 1)
            return databaseManager.userManager.getUserByUsername(username);
        System.out.println("Failed to register");
        return null;
    }

    @Override
    public PlayList getPlayList(User user) {
        return null;
    }

    @Override
    public PlayList createPlayList(User user, List<Video> videos) {
        return null;
    }

    @Override
    public User signIn(String username , String password)
    {
        password = hashToSHA1(password);
        return databaseManager.userManager.getUserByUsernameAndPassword( username , password);
    }


}
