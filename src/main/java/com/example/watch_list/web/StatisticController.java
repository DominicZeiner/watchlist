package com.example.watch_list.web;

import com.example.watch_list.service.StatisticService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;

@RestController
@RequestMapping("/api/statistics")
public class StatisticController {

    StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/timeSpent")
    public Integer getTimeSpent() {
        return statisticService.getTimeSpent();
    }

    @GetMapping("/timeSpentOnMovies")
    public Integer getTimeSpentOnMovies() {
        return statisticService.getTimeSpentOnMovies();
    }

    @GetMapping("/timeSpentOnShows")
    public Integer getTimeSpentOnShows() {
        return statisticService.getTimeSpentOnShows();
    }

    @GetMapping("/averageImdbRating")
    public Double getAverageImdbRating() {
        return statisticService.getAverageImdbRating();
    }

    @GetMapping("/averageGivenRating")
    public Double getAverageGivenRating() {
        return statisticService.getAverageGivenRating();
    }



}
