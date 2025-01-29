package com.example.watch_list.model.entities;

import jakarta.persistence.Entity;

@Entity
public class ShowEntity extends MediaEntity {

    private String seasons;
    private String episodes;
    private String startYear;
    private String endYear;
}
