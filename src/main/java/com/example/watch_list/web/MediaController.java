package com.example.watch_list.web;

import com.example.watch_list.exceptions.MediaAlreadyExistsException;
import com.example.watch_list.exceptions.MediaNotFoundException;
import com.example.watch_list.model.entries.CreateMedia;
import com.example.watch_list.model.entries.MediaEntry;
import com.example.watch_list.service.MediaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping("/{imdbId}")
    public MediaEntry getMediaByImdbId(@PathVariable String imdbId) throws MediaNotFoundException {
        return mediaService.getMediaByImdbId(imdbId);
    }

    @GetMapping
    public List<MediaEntry> getAllMedia() {
        return mediaService.getAllMedia();
    }

    @PostMapping
    public MediaEntry createMedia(@RequestBody CreateMedia media) throws MediaAlreadyExistsException {
        return mediaService.addMediaByImdbId(media);
    }

    @DeleteMapping("/{imdbId}")
    public void deleteMedia(@PathVariable String imdbId) throws MediaNotFoundException {
        mediaService.deleteMedia(imdbId);
    }


}
