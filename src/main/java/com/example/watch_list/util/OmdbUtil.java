package com.example.watch_list.util;

import org.springframework.stereotype.Service;

import java.time.Year;

@Service
public class OmdbUtil {
    public Year parseYearToStartYear(String year) {
        String[] years = year.split("–");
        int startYear = Integer.parseInt(years[0]);
        return Year.of(startYear);
    }

    public Year parseYearToEndYear(String year) {
        String[] years = year.split("–");
        if (years.length == 1) {
            return Year.of(Integer.parseInt(years[0]));
        } else if (years.length == 0) {
            return Year.of(0);
        }
        int endYear = Integer.parseInt(years[1]);
        return Year.of(endYear);
    }
}
