package com.example.avma1997.tmdb;

import java.io.Serializable;

/**
 * Created by Avma1997 on 7/27/2017.
 */

public class TV implements Serializable {
   String name;
    int id;
    double vote_average;
    String poster_path;
    String first_air_date;
    int[] genre_ids;
    String overview;

    public TV(String name, int id, double vote_average, String poster_path, String first_air_date, int[] genre_ids, String overview){
        this.name = name;
        this.id = id;
        this.vote_average = vote_average;
        this.poster_path = poster_path;
        this.first_air_date = first_air_date;
        this.genre_ids = genre_ids;
        this.overview = overview;
    }
}
