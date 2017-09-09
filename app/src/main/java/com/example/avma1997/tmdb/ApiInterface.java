package com.example.avma1997.tmdb;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Avma1997 on 7/19/2017.
 */


    public interface ApiInterface {
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apikey);
    @GET("genre/movie/list")
    Call<GenreResponse> getGenres(@Query("api_key") String apikey);
    @GET("movie/now_playing")
    Call<MovieResponse> getInTheatresMovies(@Query("api_key") String apikey);
    @GET("movie/upcoming")
    Call<MovieResponse> getUpcoming(@Query("api_key") String apikey);
    @GET("movie/top_rated")
    Call<MovieResponse> getTopRated(@Query("api_key") String apikey);
    @GET("movie/{id}/images")
    Call<MoviesImageResponse> getMovieImages(@Path("id") Integer id,@Query("api_key") String apikey);
    @GET("movie/{id}/videos")
    Call<VideoResponse> getMovieVideos(@Path("id") Integer id,@Query("api_key") String apikey);
    @GET("movie/{id}/casts")
    Call<CastResponse> getMovieCast(@Path("id") Integer id,@Query("api_key") String apikey);
    @GET("movie/{id}/reviews")
    Call<ReviewResponse> getMovieReviews(@Path("id") Integer id,@Query("api_key") String apikey);
    @GET("movie/{id}/recommendations")
    Call<MovieResponse> getSimilarMovies(@Path("id") Integer id,@Query("api_key") String apikey);
    @GET("tv/popular")
    Call<TVResponse> getPopularTVShows(@Query("api_key") String apikey);




}

