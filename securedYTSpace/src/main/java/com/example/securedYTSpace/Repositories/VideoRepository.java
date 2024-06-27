package com.example.securedYTSpace.Repositories;

import com.example.securedYTSpace.Entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
