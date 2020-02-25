package com.example.facebook.youtube;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistSnippet;
import com.google.api.services.youtube.model.PlaylistStatus;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collection;


public class ApiExample {



    public static YouTube getService() throws GeneralSecurityException, IOException {

        String APPLICATION_NAME = "API code samples";
        String childString = ".credentials/youtube-java-quickstart";

        JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
        FileDataStoreFactory DATA_STORE_FACTORY = new FileDataStoreFactory(new java.io.File( System.getProperty("user.home"), childString));

        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Collection<String> SCOPES =  Arrays.asList(YouTubeScopes.YOUTUBE_FORCE_SSL); // Arrays.asList(YouTubeScopes.YOUTUBE_FORCE_SSL);

        InputStream in = ApiExample.class.getResourceAsStream(Auth.jsonFileLocation);

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");

        return new YouTube.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }






    /**
     * Call function to create API service object. Define and
     * execute API request. Print API response.
     *
     * @throws GeneralSecurityException, IOException, GoogleJsonResponseException
     */
    public static String testInsert()
            throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        YouTube youtubeService = getService();

        // Define the Playlist object, which will be uploaded as the request body.
        Playlist playlist = new Playlist();

        // Add the snippet object property to the Playlist object.
        PlaylistSnippet snippet = new PlaylistSnippet();
        snippet.setDefaultLanguage("en");
        snippet.setDescription("This is a sample playlist description.");
        String[] tags = {
                "sample playlist",
                "API call"
        };
        snippet.setTags(Arrays.asList(tags));
        snippet.setTitle("Sample playlist created via API");
        playlist.setSnippet(snippet);

        // Add the status object property to the Playlist object.
        PlaylistStatus status = new PlaylistStatus();
        status.setPrivacyStatus("public");
        playlist.setStatus(status);

        // Define and execute the API request
        YouTube.Playlists.Insert request = youtubeService.playlists().insert("snippet,status", playlist);
        Playlist response = request.execute();
        //System.out.println(response);
        return response.toString();
    }
}