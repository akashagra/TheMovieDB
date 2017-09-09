package com.example.avma1997.tmdb;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Avma1997 on 7/23/2017.
 */

class VideoResponse {
    @SerializedName("results")
    private ArrayList<Videos> videoList;

    public ArrayList<Videos> getVideoList() {
        return videoList;
    }

    public void setMovieList(ArrayList<Videos> movieList) {
        this.videoList = videoList;
    }
}
