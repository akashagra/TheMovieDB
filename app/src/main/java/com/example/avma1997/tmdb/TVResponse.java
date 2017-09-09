package com.example.avma1997.tmdb;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Avma1997 on 7/27/2017.
 */

public class TVResponse {
    @SerializedName("results")
    private ArrayList<TV> tvShows;

    public ArrayList<TV> getTVShowsList() {
        return tvShows;
    }

    public void setTVShowsList(ArrayList<TV> tvShows) {
        this.tvShows = tvShows;
    }
}
