package com.example.watch_list.web;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/index.html")
    public String homePage() {
        return "index";
    }

    @GetMapping("/watchlist.html")
    public String watchlistPage() {
        return "watchlist";
    }

    @GetMapping("/statistics.html")
    public String statisticsPage() {
        return "statistics";
    }
}
