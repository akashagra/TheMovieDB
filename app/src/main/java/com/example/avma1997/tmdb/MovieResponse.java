package com.example.avma1997.tmdb;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Avma1997 on 7/19/2017.
 */

public class MovieResponse {
    @SerializedName("results")
    private ArrayList<Moviedb> movieList;

    public ArrayList<Moviedb> getMovieList() {
        return movieList;
    }

    public void setMovieList(ArrayList<Moviedb> movieList) {
        this.movieList = movieList;
    }
}