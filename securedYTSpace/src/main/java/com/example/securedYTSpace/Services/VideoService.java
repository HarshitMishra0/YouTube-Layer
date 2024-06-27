package com.example.securedYTSpace.Services;

import com.example.securedYTSpace.Entities.Video;
import com.example.securedYTSpace.Repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public Video uploadVideo(Video video) {
        return videoRepository.save(video);
    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public Video getVideoById(Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found with id " + id));
    }

    @Transactional
    public Video approveVideo(Long id) {
        Video video = getVideoById(id);
        video.setApproved(true);
        return videoRepository.save(video);
    }
}
