package com.example.watch_list.repository;

import com.example.watch_list.model.entities.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MediaRepository extends JpaRepository<MediaEntity, UUID> {

    Optional<MediaEntity> findByImdbId(String imdbId);
    boolean existsByImdbId(String imdbId);
    void deleteByImdbId(String imdbId);
}
