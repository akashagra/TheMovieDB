package com.example.avma1997.tmdb;

import java.util.ArrayList;

/**
 * Created by Avma1997 on 7/20/2017.
 */

public class GenreResponse {
    private ArrayList<Genre> genres;
}
class Genre
{
    int id;
    String name;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
