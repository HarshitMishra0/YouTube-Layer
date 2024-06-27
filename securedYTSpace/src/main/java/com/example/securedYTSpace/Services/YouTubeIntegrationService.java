package com.example.securedYTSpace.Services;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.gson.GsonFactory; // Import GsonFactory
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class YouTubeIntegrationService {

    @Value("${youtube.credentials.filepath}")
    private String credentialsFilePath;

    @Value("${youtube.application.name}")
    private String applicationName;

    @Value("${youtube.video.privacyStatus}")
    private String videoPrivacyStatus;

    private YouTube youtube;

    public void initializeYouTube() throws IOException, GeneralSecurityException {
        GoogleCredential credential = GoogleCredential
                .fromStream(new FileInputStream(credentialsFilePath))
                .createScoped(Collections.singleton("https://www.googleapis.com/auth/youtube.upload"));

        youtube = new YouTube.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                new GsonFactory(), // Use GsonFactory instead of JacksonFactory
                credential)
                .setApplicationName(applicationName)
                .build();
    }

    public String uploadVideo(File videoFile, String title, String description) throws IOException, GeneralSecurityException {
        initializeYouTube();

        Video videoObjectDefiningMetadata = new Video();

        VideoSnippet snippet = new VideoSnippet();
        snippet.setTitle(title);
        snippet.setDescription(description);
        videoObjectDefiningMetadata.setSnippet(snippet);

        VideoStatus status = new VideoStatus();
        status.setPrivacyStatus(videoPrivacyStatus);
        videoObjectDefiningMetadata.setStatus(status);

        InputStreamContent mediaContent = new InputStreamContent(
                "video/*",
                new FileInputStream(videoFile));

        YouTube.Videos.Insert videoInsert = youtube.videos()
                .insert(Collections.singletonList("snippet,status"),
                        videoObjectDefiningMetadata,
                        mediaContent);

        Video returnedVideo = videoInsert.execute();
        return returnedVideo.getId();
    }

    // Other methods for managing metadata, retrieving video details, etc.
}
