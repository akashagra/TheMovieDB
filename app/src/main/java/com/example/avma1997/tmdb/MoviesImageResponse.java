package com.example.avma1997.tmdb;

import java.util.ArrayList;

/**
 * Created by Avma1997 on 7/22/2017.
 */

 public class MoviesImageResponse {
    private ArrayList<Movieimage> backdrops;

    public ArrayList<Movieimage> getImageList() {
        return backdrops;
    }

    public void setImageList(ArrayList<Movieimage> backdrops) {
        this.backdrops = backdrops;
    }
}
