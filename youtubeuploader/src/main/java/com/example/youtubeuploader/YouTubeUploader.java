package com.example.youtubeuploader;


import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Component
public class YouTubeUploader {

    private static final String APPLICATION_NAME = "YouTubeUploader";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private YouTube youtube;

    public YouTubeUploader() throws GeneralSecurityException, IOException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        youtube = new YouTube.Builder(httpTransport, JSON_FACTORY, null)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public String uploadVideo(MultipartFile file) throws IOException {
        Video videoObjectDefiningMetadata = new Video();

        VideoSnippet snippet = new VideoSnippet();
        snippet.setTitle("Test Title");
        snippet.setDescription("Test Description");
        // Set other video metadata as needed
        videoObjectDefiningMetadata.setSnippet(snippet);

        VideoStatus status = new VideoStatus();
        status.setPrivacyStatus("private"); // or "public", "unlisted"
        videoObjectDefiningMetadata.setStatus(status);

        YouTube.Videos.Insert videoInsertRequest = youtube.videos()
                .insert(String.valueOf(Collections.singletonList("snippet,status")), videoObjectDefiningMetadata,
                        new com.google.api.client.http.ByteArrayContent("video/*", file.getBytes()));
        videoInsertRequest.setKey("YOUR_API_KEY"); // Replace with your actual API key

        Video returnedVideo = videoInsertRequest.execute();

        return returnedVideo.getId();
    }
}
