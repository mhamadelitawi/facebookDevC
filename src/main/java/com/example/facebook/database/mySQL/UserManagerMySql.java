package com.example.facebook.database.mySQL;


import com.example.facebook.manager.UserManager;
import com.example.facebook.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import static com.example.facebook.Utils.Tools.hashToSHA1;

@Service
public class UserManagerMySql implements UserManager {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int createUser(String username, String password) {
        return jdbcTemplate.update("insert into user (username, password) values(?,?) ", username, password  );
    }

    @Override
    public User getUserByUsername(String username) {
        return jdbcTemplate.queryForObject("select * from user where username = ?",  new Object[]{username} , new UserMapper());
    }

    @Override
    public User getUserByUsernameAndPassword(String username , String password) {
        return jdbcTemplate.queryForObject("select * from user where username = ? and password = ? ",  new Object[]{username , password} , new UserMapper());
    }

}
