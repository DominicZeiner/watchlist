package com.example.watch_list.service.omdb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class OmdbServiceIntegrationTest {

    @Autowired
    private OmdbService omdbService;

    @Test
    void fetchResponseByImdbId_validId_returnResponse() {
        // given
        String imdbId = "tt0398808";

        // when
        OmdbResponse response = omdbService.fetchResponseByImdbId(imdbId);

        // then
        assertEquals("Title should be 'Bridge to Terabithia'", "Bridge to Terabithia", response.getTitle());
        assertEquals("Type should be 'movie'", "movie", response.getType());
    }


}
