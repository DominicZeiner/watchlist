package com.example.watch_list.repository;

import com.example.watch_list.model.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<MovieEntity, UUID> {

    Optional<MovieEntity> findByImdbId(String imdbId);

    void deleteByImdbId(String imdbId);
}
