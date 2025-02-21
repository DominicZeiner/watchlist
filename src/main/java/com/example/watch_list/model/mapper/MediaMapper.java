package com.example.watch_list.model.mapper;

import com.example.watch_list.model.entities.MediaEntity;
import com.example.watch_list.model.entities.MovieEntity;
import com.example.watch_list.model.entities.ShowEntity;
import com.example.watch_list.model.entries.MediaEntry;
import org.springframework.stereotype.Component;

@Component
public class MediaMapper {
    public MediaEntry toMediaEntry(MediaEntity mediaEntity) {
        if (mediaEntity instanceof MovieEntity) {
            return toMovieEntry((MovieEntity) mediaEntity);
        } else if (mediaEntity instanceof ShowEntity) {
            return toShowEntry((ShowEntity) mediaEntity);
        } else {
            throw new IllegalArgumentException("Unsupported media type");
        }
    }

    private MediaEntry toMovieEntry(MovieEntity movieEntity) {
        return new MediaEntry(
                movieEntity.getImdbId(),
                movieEntity.getTitle(),
                movieEntity.getGenre(),
                movieEntity.getImdbRating(),
                movieEntity.getRuntime()
        );
    }

    private MediaEntry toShowEntry(ShowEntity showEntity) {
        return new MediaEntry(
                showEntity.getImdbId(),
                showEntity.getTitle(),
                showEntity.getGenre(),
                showEntity.getImdbRating(),
                showEntity.getRuntime()
        );
    }
}
