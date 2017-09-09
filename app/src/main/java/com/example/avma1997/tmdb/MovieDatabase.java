package com.example.avma1997.tmdb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

/**
 * Created by Avma1997 on 8/2/2017.
 */
@Database(entities = {Moviedb.class}, version = 1)
@TypeConverters({Convertors.class})
public abstract class MovieDatabase extends RoomDatabase {
    private static MovieDatabase INSTANCE1;
    private static MovieDatabase INSTANCE2;
    private static MovieDatabase INSTANCE3;
    private static MovieDatabase INSTANCE4;
    private static MovieDatabase INSTANCE5;
    private static MovieDatabase INSTANCE6;

    private static Object LOCK = new Object();

    public static MovieDatabase getInstancePopular(Context context){
        if(INSTANCE1 == null){
            synchronized (LOCK){
                if(INSTANCE1 == null){
                    INSTANCE1 = Room.databaseBuilder(context.getApplicationContext()
                            ,MovieDatabase.class,MovieDatabase.DB_NAME).build();
                }
            }
        }
        return INSTANCE1;
    }




    public static MovieDatabase getInstanceComingSoon(Context context){
        if(INSTANCE2 == null){
            synchronized (LOCK){
                if(INSTANCE2 == null){
                    INSTANCE2 = Room.databaseBuilder(context.getApplicationContext()
                            ,MovieDatabase.class,"movies_db_comingsoon").build();
                }
            }
        }
        return INSTANCE2;



    }
    public static MovieDatabase getInstanceTopRated(Context context){
        if(INSTANCE3 == null){
            synchronized (LOCK){
                if(INSTANCE3 == null){
                    INSTANCE3 = Room.databaseBuilder(context.getApplicationContext()
                            ,MovieDatabase.class,"movies_db_toprated").build();
                }
            }
        }
        return INSTANCE3;



    }
    public static MovieDatabase getInstanceTheatres(Context context){
        if(INSTANCE4 == null){
            synchronized (LOCK){
                if(INSTANCE4 == null){
                    INSTANCE4 = Room.databaseBuilder(context.getApplicationContext()
                            ,MovieDatabase.class,"movies_db_theatres").build();
                }
            }
        }
        return INSTANCE4;



    }

    public static MovieDatabase getInstanceFav(Context context) {
        if (INSTANCE5 == null) {
            synchronized (LOCK) {
                if (INSTANCE5== null) {
                    INSTANCE5 = Room.databaseBuilder(context.getApplicationContext()
                            , MovieDatabase.class, "movies_db_fav").build();
                }
            }
        }
        return INSTANCE5;
    }
    public static MovieDatabase getInstanceWatch(Context context) {
        if (INSTANCE6 == null) {
            synchronized (LOCK) {
                if (INSTANCE6== null) {
                    INSTANCE6 = Room.databaseBuilder(context.getApplicationContext()
                            , MovieDatabase.class, "movies_db_watch").build();
                }
            }
        }
        return INSTANCE6;
    }





    public static final String DB_NAME = "movies_db";

    abstract MovieDao movieDao();



}

