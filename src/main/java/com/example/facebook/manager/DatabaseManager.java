package com.example.facebook.manager;

import org.springframework.stereotype.Service;

@Service
public class DatabaseManager {
    
    public UserManager userManager = null;


    public DatabaseManager() {
    }

    public DatabaseManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
