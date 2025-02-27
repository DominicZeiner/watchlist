package com.example.watch_list.web;

import com.example.watch_list.exceptions.MediaNotFoundException;
import com.example.watch_list.exceptions.WatchlistNotFoundException;
import com.example.watch_list.exceptions.WatchlistEntryAlreadyExistsException;
import com.example.watch_list.model.entries.*;
import com.example.watch_list.service.WatchlistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {

    private final WatchlistService watchlistService;

    public WatchlistController(WatchlistService watchlistService) {
        this.watchlistService = watchlistService;
    }


    @PostMapping
    public WatchlistEntry createWatchlistEntry(@RequestBody CreateWatchlist createWatchlist) throws WatchlistEntryAlreadyExistsException, MediaNotFoundException {
        return watchlistService.addWatchlistEntry(createWatchlist);
    }

    @PutMapping("/{imdbId}")
    public WatchlistEntry updateWatchlistEntry(@PathVariable UUID imdbId, @RequestBody WatchlistEntry watchListEntry) {
        return null;
    }

    @DeleteMapping("/{imdbId}")
    public void deleteWatchlistEntry(@PathVariable String imdbId) {
        watchlistService.deleteWatchlistEntry(imdbId);
    }

    @GetMapping("/favorites")
    public List<WatchlistEntry> getFavoriteEntries() {
        return watchlistService.getFavoriteEntries();
    }

    @GetMapping("/watched")
    public List<WatchlistEntry> getWatchedEntries() {
        return watchlistService.getWatchedEntries();
    }

    @GetMapping
    public List<GetWatchListWithMedia> getWatchlist() {
        return watchlistService.getWatchlistWithMedia();
    }

    @GetMapping("/available")
    public List<AvailableMedia> getImdbIdsNotInWatchlist() {
        return watchlistService.getImdbIdsNotInWatchlist();
    }

    @PatchMapping("/{imdbId}")
    public UpdateWatchlistRating updateWatchlistRating(@PathVariable String imdbId, @RequestBody UpdateWatchlistRating updateWatchlistRating) throws WatchlistNotFoundException, MediaNotFoundException {
        return watchlistService.updateWatchlistRating(imdbId, updateWatchlistRating);
    }






}
