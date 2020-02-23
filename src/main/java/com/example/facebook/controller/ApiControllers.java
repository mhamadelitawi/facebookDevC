package com.example.facebook.controller;

import com.example.facebook.database.mySQL.UserManagerMySql;
import com.example.facebook.manager.ControllerManager;
import com.example.facebook.manager.DatabaseManager;
import com.example.facebook.manager.UserManager;
import com.example.facebook.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiControllers {

    @Autowired
    UserManagerMySql userManagerMySql;


    @GetMapping("/test")
    @ResponseBody
    public String test()
    {
        return "test";

    }


    @GetMapping("/register")
    @ResponseBody
    public User register(@RequestParam(name="username", required=true) String username ,
                       @RequestParam(name="password", required=true) String password)
    {
        System.out.println("test");

        ControllerManager manager = new ControllerManager( new DatabaseManager( userManagerMySql )  );
        User user =  manager.register(username , password);
        return user;
    }


    @GetMapping("/signIn")
    @ResponseBody
    public User signIn(@RequestParam(name="username", required=true) String username ,
                       @RequestParam(name="password", required=true) String password)
    {
        ControllerManager manager = new ControllerManager( new DatabaseManager( userManagerMySql )  );
        User user =  manager.signIn(username , password);
        return user;

    }

}
