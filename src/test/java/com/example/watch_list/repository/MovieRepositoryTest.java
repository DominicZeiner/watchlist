package com.example.watch_list.repository;

import com.example.watch_list.model.entities.MovieEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Year;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieRepositoryTest {

    private MovieRepository movieRepository;

    @BeforeEach
    public void setUp() {
        movieRepository = Mockito.mock(MovieRepository.class);
    }

    @Test
    void testCrudOperations() {
        MovieEntity movie = new MovieEntity();
        movie.setId(UUID.randomUUID());
        movie.setImdbId("tt1234567");
        movie.setTitle("Test Movie");
        movie.setReleaseYear(Year.of(2001));
        movie.setRuntime(120);
        movie.setGenre("Action");
        movie.setImdbRating(7.5);

        // Mocking repository methods
        when(movieRepository.save(any(MovieEntity.class))).thenReturn(movie);
        when(movieRepository.findByImdbId("tt1234567")).thenReturn(Optional.of(movie));
        when(movieRepository.existsByImdbId("tt1234567")).thenReturn(true);

        // Create
        MovieEntity savedMovie = movieRepository.save(movie);
        assertEquals("Test Movie", savedMovie.getTitle());

        // Read
        Optional<MovieEntity> fetchedMovie = movieRepository.findByImdbId("tt1234567");
        assertTrue(fetchedMovie.isPresent());
        assertEquals("Test Movie", fetchedMovie.get().getTitle());

        // Update
        MovieEntity existingMovie = fetchedMovie.get();
        existingMovie.setTitle("Updated Movie");
        when(movieRepository.save(existingMovie)).thenReturn(existingMovie);
        when(movieRepository.findByImdbId("tt1234567")).thenReturn(Optional.of(existingMovie));

        MovieEntity updatedMovie = movieRepository.save(existingMovie);
        assertTrue(movieRepository.existsByImdbId("tt1234567"));
        assertEquals("Updated Movie", movieRepository.findByImdbId("tt1234567").get().getTitle());

        // Delete
        doNothing().when(movieRepository).delete(existingMovie);
        movieRepository.delete(existingMovie);
        when(movieRepository.findByImdbId("tt1234567")).thenReturn(Optional.empty());

        assertFalse(movieRepository.findByImdbId("tt1234567").isPresent());

        // Verify interactions
        verify(movieRepository, times(2)).save(any(MovieEntity.class));
        verify(movieRepository).delete(existingMovie);
    }

}