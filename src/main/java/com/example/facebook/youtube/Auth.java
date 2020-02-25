package com.example.facebook.youtube;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;


public class Auth {

    public static String jsonFileLocation = "/static/client_secret.json";

    /** Application name. */
    public static final String APPLICATION_NAME = "API Sample";

    /** Directory to store user credentials for this application. */
   // public static final java.io.File DATA_STORE_DIR = new java.io.File( System.getProperty("user.home"), ".credentials/youtube-java-quickstart");

    /** Global instance of the {@link FileDataStoreFactory}. */
    public static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    public static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    public static HttpTransport HTTP_TRANSPORT;


    public static final List<String> SCOPES = Arrays.asList(YouTubeScopes.YOUTUBE_FORCE_SSL); //YouTubeScopes.YOUTUBE_READONLY
            //Arrays.asList("https://www.googleapis.com/auth/youtube.force-ssl");


//    /** Manage your YouTube account. */
//    public static final String YOUTUBE = "https://www.googleapis.com/auth/youtube";
//
//    /** See, edit, and permanently delete your YouTube videos, ratings, comments and captions. */
//    public static final String YOUTUBE_FORCE_SSL = "https://www.googleapis.com/auth/youtube.force-ssl";
//
//    /** View your YouTube account. */
//    public static final String YOUTUBE_READONLY = "https://www.googleapis.com/auth/youtube.readonly";
//
//    /** Manage your YouTube videos. */
//    public static final String YOUTUBE_UPLOAD = "https://www.googleapis.com/auth/youtube.upload";
//
//    /** View and manage your assets and associated content on YouTube. */
//    public static final String YOUTUBEPARTNER = "https://www.googleapis.com/auth/youtubepartner";
//
//    /** View private information of your YouTube channel relevant during the audit process with a YouTube partner. */
//    public static final String YOUTUBEPARTNER_CHANNEL_AUDIT = "https://www.googleapis.com/auth/youtubepartner-channel-audit";






    public static Credential authorize() throws IOException, GeneralSecurityException {


        if(HTTP_TRANSPORT == null || DATA_STORE_FACTORY == null )
        {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        //    DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        }


        // Load client secrets.
        InputStream in = Auth.class.getResourceAsStream(jsonFileLocation);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        return credential;
    }



    public static YouTube getYouTubeService() throws IOException, GeneralSecurityException {
        Credential credential = authorize();
        return new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();

    }







    public static void testYoutube() throws IOException, GeneralSecurityException {
        YouTube youtube = Auth.getYouTubeService();
        try {
            YouTube.Channels.List channelsListByUsernameRequest = youtube.channels().list("snippet,contentDetails,statistics");
            channelsListByUsernameRequest.setForUsername("GoogleDevelopers"); //GoogleDevelopers

            ChannelListResponse response = channelsListByUsernameRequest.execute();
            Channel channel = response.getItems().get(0);
            System.out.printf( "This channel's ID is %s. Its title is '%s', and it has %s views.\n", channel.getId(),
                    channel.getSnippet().getTitle(),  channel.getStatistics().getViewCount());
        } catch (GoogleJsonResponseException e) {
            e.printStackTrace();
            System.err.println("There was a service error: " +
                    e.getDetails().getCode() + " : " + e.getDetails().getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }




//    public static void main(String[] args) throws IOException {
//        YouTube youtube = getYouTubeService();
//        try {
//            YouTube.Channels.List channelsListByUsernameRequest = youtube.channels().list("snippet,contentDetails,statistics");
//            channelsListByUsernameRequest.setForUsername("GoogleDevelopers");
//
//            ChannelListResponse response = channelsListByUsernameRequest.execute();
//            Channel channel = response.getItems().get(0);
//            System.out.printf(
//                "This channel's ID is %s. Its title is '%s', and it has %s views.\n",
//                channel.getId(),
//                channel.getSnippet().getTitle(),
//                channel.getStatistics().getViewCount());
//        } catch (GoogleJsonResponseException e) {
//            e.printStackTrace();
//            System.err.println("There was a service error: " +
//                e.getDetails().getCode() + " : " + e.getDetails().getMessage());
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//    }
}
