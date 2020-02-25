package com.example.facebook.manager;

import com.example.facebook.model.AccessType;
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
    public PlayList createPlayList(Integer userId , String title )
    {
       return databaseManager.playListManager.createPlaylist(  title ,   userId);
    }

    @Override
    public User signIn(String username , String password)
    {
        password = hashToSHA1(password);
        return databaseManager.userManager.getUserByUsernameAndPassword( username , password);
    }

    @Override
    public void grantAccess(Integer playListId, Integer userId, AccessType type) {

        if(AccessType.GRANT.equals(type))
             databaseManager.playListManager.grantAccess( playListId, userId);
        else
            databaseManager.playListManager.removeAccess( playListId, userId);
    }


}
