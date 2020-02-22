package com.example.facebook.controller;

import com.example.facebook.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiControllers {


    @GetMapping("/hello-world")
    @ResponseBody
    public User signIn(@RequestParam(name="username", required=true) String username ,
                       @RequestParam(name="password", required=true) String password)
    {







        return null;

    }


}
