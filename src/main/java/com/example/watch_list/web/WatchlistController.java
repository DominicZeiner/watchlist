package com.example.watch_list.web;

import com.example.watch_list.exceptions.MediaNotFoundException;
import com.example.watch_list.exceptions.WatchlistEntryAlreadyExistsException;
import com.example.watch_list.model.entries.AvailableMedia;
import com.example.watch_list.model.entries.CreateWatchlist;
import com.example.watch_list.model.entries.GetWatchListWithMedia;
import com.example.watch_list.model.entries.WatchListEntry;
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
    public WatchListEntry createWatchlistEntry(@RequestBody CreateWatchlist createWatchlist) throws WatchlistEntryAlreadyExistsException, MediaNotFoundException {
        return watchlistService.addWatchlistEntry(createWatchlist);
    }

    @PutMapping("/{id}")
    public WatchListEntry updateWatchlistEntry(@PathVariable UUID id, @RequestBody WatchListEntry watchListEntry) {
        return null;
    }

    @DeleteMapping("/{imdbId}")
    public void deleteWatchlistEntry(@PathVariable String imdbId) {
        watchlistService.deleteWatchlistEntry(imdbId);
    }

    @GetMapping("/favorites")
    public List<WatchListEntry> getFavoriteEntries() {
        return watchlistService.getFavoriteEntries();
    }

    @GetMapping("/watched")
    public List<WatchListEntry> getWatchedEntries() {
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







}
