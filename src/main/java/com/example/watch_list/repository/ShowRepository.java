package com.example.watch_list.repository;

import com.example.watch_list.model.entities.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ShowRepository extends JpaRepository<ShowEntity, UUID> {
    Optional<ShowEntity> findByImdbId(String imdbId);
    void deleteByImdbId(String imdbId);
    boolean existsByImdbId(String imdbId);
}
