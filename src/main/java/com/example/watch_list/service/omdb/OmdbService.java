package com.example.watch_list.service.omdb;


import com.example.watch_list.model.entities.MediaEntity;
import com.example.watch_list.model.entities.MovieEntity;
import com.example.watch_list.model.entities.ShowEntity;
import com.example.watch_list.util.OmdbUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class OmdbService {

    private final WebClient webClient;
    private final OmdbUtil omdbUtil;

    @Value("${omdb.api.key}")
    private String apiKey;


    public OmdbService(OmdbUtil omdbUtil, WebClient webClient) {
        this.webClient = webClient;
        this.omdbUtil = omdbUtil;
    }

    public OmdbResponse fetchResponseByImdbId(String imdbId) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("apikey", apiKey)
                        .queryParam("i", imdbId)
                        .build())
                .retrieve()
                .bodyToMono(OmdbResponse.class)
                .block();
    }


    public MediaEntity convertOmdbResponseToMediaEntity(OmdbResponse omdbResponse) {
        if (omdbResponse.getType().equalsIgnoreCase("movie")) {
            return new MovieEntity(
                    omdbResponse.getImdbId(),
                    omdbResponse.getTitle(),
                    omdbResponse.getGenre(),
                    Double.parseDouble(omdbResponse.getImdbRating()),
                    Integer.parseInt(omdbResponse.getRuntime().replaceAll("\\D", "")),

                    omdbUtil.parseYearToStartYear(omdbResponse.getYear())
            );
        } else if (omdbResponse.getType().equalsIgnoreCase("series")) {
            return new ShowEntity(
                    omdbResponse.getImdbId(),
                    omdbResponse.getTitle(),
                    omdbResponse.getGenre(),
                    Double.parseDouble(omdbResponse.getImdbRating()),
                    Integer.parseInt(omdbResponse.getRuntime().replaceAll("\\D", "")),

                    Integer.parseInt(omdbResponse.getTotalSeasons()),
                    -1,
                    omdbUtil.parseYearToStartYear(omdbResponse.getYear()),
                    omdbUtil.parseYearToEndYear(omdbResponse.getYear())

            );
        } else {
            throw new IllegalArgumentException("Unsupported media type");
        }
    }
}
