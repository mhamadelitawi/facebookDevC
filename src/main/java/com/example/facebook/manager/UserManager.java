package com.example.facebook.manager;

import com.example.facebook.model.User;

public interface UserManager {


    public int createUser(String username , String password);

    public User getUserByUsername(String username);

    public User getUserByUsernameAndPassword(String username , String password);

}
