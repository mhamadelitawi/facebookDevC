package com.example.facebook.manager;

import org.springframework.stereotype.Service;

@Service
public class DatabaseManager {
    
    public UserManager userManager = null;
    public PlayListManager playListManager = null;


    public DatabaseManager() {
    }

    public DatabaseManager(UserManager userManager) {
        this.userManager = userManager;
    }


    public DatabaseManager(PlayListManager playListManager) {
        this.playListManager = playListManager;
    }



}
