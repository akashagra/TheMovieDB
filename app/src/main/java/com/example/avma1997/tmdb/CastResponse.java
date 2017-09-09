package com.example.avma1997.tmdb;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Avma1997 on 7/24/2017.
 */

 public class CastResponse {

    @SerializedName("cast")
    private ArrayList<Cast> castlist;

    public ArrayList<Cast> getCastList() {
        return castlist;
    }

    public void setMovieList(ArrayList<Cast> castlist) {
        this.castlist = castlist;
    }




}
