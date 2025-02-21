package com.example.watch_list.model.mapper;

import com.example.watch_list.model.entities.ShowEntity;
import com.example.watch_list.model.entries.ShowEntry;
import org.springframework.stereotype.Component;

@Component
public class ShowMapper {
    public ShowEntry toShowEntry(ShowEntity showEntity) {
        return new ShowEntry(
                showEntity.getImdbId(),
                showEntity.getTitle(),
                showEntity.getGenre(),
                showEntity.getImdbRating(),
                showEntity.getRuntime(),
                showEntity.getSeasons(),
                showEntity.getEpisodes(),
                showEntity.getStartYear(),
                showEntity.getEndYear()
        );
    }

    public static ShowEntity toShowEntity(ShowEntry showEntry) {
        return null;
    }



}
