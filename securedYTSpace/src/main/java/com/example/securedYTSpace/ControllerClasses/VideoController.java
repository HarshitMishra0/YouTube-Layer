package com.example.securedYTSpace.ControllerClasses;

import com.example.securedYTSpace.Entities.Video;
import com.example.securedYTSpace.Services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/upload")
    @PreAuthorize("hasRole('UPLOADER')")
    public ResponseEntity<?> uploadVideo(@RequestBody Video video) {
        Video savedVideo = videoService.uploadVideo(video);
        return ResponseEntity.ok(savedVideo);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<?> getAllVideos() {
        List<Video> videos = videoService.getAllVideos();
        return ResponseEntity.ok(videos);
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<?> approveVideo(@PathVariable Long id) {
        Video approvedVideo = videoService.approveVideo(id);
        return ResponseEntity.ok(approvedVideo);
    }
}
