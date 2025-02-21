package com.example.watch_list.web;

import com.example.watch_list.exceptions.MediaNotFoundException;
import com.example.watch_list.model.entities.ShowEntity;
import com.example.watch_list.model.entries.ShowEntry;
import com.example.watch_list.service.ShowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping
    public List<ShowEntry> getAllShows() {
        return showService.getAllShows();
    }

    @GetMapping("/{imdbId}")
    public ShowEntry getShowByImdbId(@PathVariable String imdbId) throws MediaNotFoundException {
        return showService.getShowByImdbId(imdbId);
    }
}
