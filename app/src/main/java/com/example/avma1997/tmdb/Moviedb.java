package com.example.avma1997.tmdb;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Avma1997 on 7/19/2017.
 */
@Entity
 public class Moviedb implements Serializable {
    @PrimaryKey
    int id;
    String title;

    String overview;
    double vote_average;
    String poster_path;
    String release_date;
    String backdrop_path;

    int[] genre_ids;



    public Moviedb(String title, String overview, double vote_average, String poster_path,String release_date,int[] genre_ids) {
        this.title = title;
        this.overview = overview;
        this.vote_average = vote_average;
        this.poster_path = poster_path;
        this.release_date=release_date;
        this.genre_ids=genre_ids;

    }

}
@Dao
interface MovieDao{
    @Query("Select * from Moviedb")
    List<Moviedb> loadMovies();
    @Insert
    void insertAll(Moviedb... movies);
    @Update
    void update(Moviedb... movies);
}





