package com.example.watch_list.service;

import com.example.watch_list.exceptions.MediaNotFoundException;
import com.example.watch_list.model.entities.ShowEntity;
import com.example.watch_list.model.entries.ShowEntry;
import com.example.watch_list.model.mapper.ShowMapper;
import com.example.watch_list.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final ShowMapper showMapper;

    public ShowService(ShowRepository showRepository, ShowMapper showMapper) {
        this.showRepository = showRepository;
        this.showMapper = showMapper;
    }



    public void deleteShow(String imdbId) {
        showRepository.deleteByImdbId(imdbId);
    }

    public List<ShowEntry> getAllShows() {
        return showRepository.findAll().stream()
                .map(showMapper::toShowEntry)
                .toList();
    }

    public ShowEntry getShowByImdbId(String imdbId) throws MediaNotFoundException {
        Optional<ShowEntity> showEntity = showRepository.findByImdbId(imdbId);
        if (showEntity.isEmpty()) {
            throw new MediaNotFoundException("Show with imdbId " + imdbId + " not found");
        }
        return showMapper.toShowEntry(showEntity.get());
    }
}
