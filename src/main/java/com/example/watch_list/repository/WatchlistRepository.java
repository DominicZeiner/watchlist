package com.example.watch_list.repository;

import com.example.watch_list.model.entities.WatchlistEntity;
import com.example.watch_list.model.entities.WatchlistStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WatchlistRepository extends JpaRepository<WatchlistEntity, UUID> {
    List<WatchlistEntity> findByStatus(WatchlistStatus status);
    List<WatchlistEntity> findByIsFavorite(boolean favorite);

    Optional<WatchlistEntity> findByMediaEntityId(UUID id);
    void deleteByMediaEntityId(UUID id);
}
