package com.example.facebook.controller;

import com.example.facebook.database.mySQL.UserManagerMySql;
import com.example.facebook.manager.ControllerManager;
import com.example.facebook.manager.DatabaseManager;
import com.example.facebook.model.User;
import com.example.facebook.youtube.Auth;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.security.GeneralSecurityException;

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

    @GetMapping("/youTubeTest")
    @ResponseBody
    public String youTubeTest(
            @RequestParam(name="part", required=false) String part,
            @RequestParam(name="isMine", required=false) Boolean isMine,
            @RequestParam(name="id", required=false) String id,
            @RequestParam(name="channelId", required=false) String channelId

    )


            throws GeneralSecurityException, IOException {

        if(part == null )
            part = "snippet,contentDetails,statistics,brandingSettings";


         YouTube youtubeService = Auth.getYouTubeService();
//
//        YouTube.Channels.List request = youtubeService.channels()
//                .list(part);
//
//        if(id != null)
//         request.setId(id); //PLgca0gRh5aLcNX_31uwCfu1oYXzfwhGbg
//
//        if(isMine != null)
//            request.setMine(isMine);
//
//        ChannelListResponse response = request.execute();


        YouTube.Playlists.List request = youtubeService.playlists().list(part);

        if(isMine != null)
             request.setMine(isMine);

        if(channelId != null)
            request.setChannelId(channelId); //UCm-MDGAMQxN2sc38fOBC7mA

        //request.setFields("items/contentDetails,nextPageToken,pageInfo");
        PlaylistListResponse response = request.execute();




        System.out.println(response);
        return response.toString();

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
