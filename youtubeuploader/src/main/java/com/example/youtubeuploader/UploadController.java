package com.example.youtubeuploader;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private final YouTubeUploader youtubeUploader;

    @Autowired
    public UploadController(YouTubeUploader youtubeUploader) {
        this.youtubeUploader = youtubeUploader;
    }

    @PostMapping("/video")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String videoId = youtubeUploader.uploadVideo(file);
            return ResponseEntity.ok("Video uploaded successfully with ID: " + videoId);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload video: " + e.getMessage());
        }
    }
}
