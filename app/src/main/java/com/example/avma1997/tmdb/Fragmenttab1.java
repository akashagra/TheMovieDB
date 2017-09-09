package com.example.avma1997.tmdb;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Avma1997 on 7/19/2017.
 */

 public class Fragmenttab1 extends Fragment {

    RecyclerView mRecyclerView;
    ArrayList<Moviedb> movies;
    RecyclerAdapter mAdapter;
    RecyclerAdapter2 mAdapter2;
    MoviesFragmentListItemClick mListener;
    int icon;
    DividerItemDecoration decoration;



    void  setCoursesFragmentListItemClick(MoviesFragmentListItemClick listener){
        this.mListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie_list, container, false);
        movies = new ArrayList<>();
        Bundle b = getArguments();

        setHasOptionsMenu(true);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mAdapter = new RecyclerAdapter(getContext(), movies, new RecyclerAdapter.MoviesClickListener() {
            public void onItemClick(View view, int position) {
                if (mListener != null) {
                    mListener.onListItemClicked(movies.get(position));
                }
            }


        });


        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(decoration);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        MovieDatabase db = MovieDatabase.getInstancePopular(getContext());
        final MovieDao movieDao = db.movieDao();
        new AsyncTask<Void, Void, ArrayList<Moviedb>>() {

            @Override
            protected ArrayList<Moviedb> doInBackground(Void... voids) {
                ArrayList movies= (ArrayList) movieDao.loadMovies();

                return movies;
            }

            @Override
            protected void onPostExecute(ArrayList<Moviedb> movies){
                if(movies.size()==0)
                {fetchMovies();}
                else
                {onDownloadComplete(movies);
                 fetchAndUpdate();
                }
            }
        }.execute();




       return v;


    }
    private void fetchAndUpdate(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<MovieResponse> call = apiInterface.getPopularMovies("69a57d4b7c3b085d539f991b8a27ebce");
        // asynchronous retrofit call using enqueue,execute can be used for synchronous calls
        call.enqueue(new Callback<MovieResponse>() {

            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.i("reach", "aa raha h");
                MovieResponse movieResponse = response.body();
                final ArrayList<Moviedb> movieArrayList = movieResponse.getMovieList();

                String t = movieArrayList.get(0).title;

                Log.i("mytag", t);
                onDownloadComplete(movieArrayList);
                MovieDatabase db = MovieDatabase.getInstancePopular(getContext());
                final MovieDao movieDao = db.movieDao();
                new AsyncTask<Void, Void, Void>() {
                    protected Void doInBackground(Void... voids) {
                        movieDao.update(movieArrayList.toArray( new Moviedb[movieArrayList.size()]));
                        return null;

                    }

                }.execute();
            }







            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });





    }
    private void fetchMovies() {

//        String urlString = "https://codingninjas.in/api/v1/courses";
//        CoursesAsyncTask courseAsyncTask = new CoursesAsyncTask();
//        courseAsyncTask.execute(urlString);
//        courseAsyncTask.setOnDownloadCompleteListener(this);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<MovieResponse> call = apiInterface.getPopularMovies("69a57d4b7c3b085d539f991b8a27ebce");
        // enqueue function takes place on different thread and is asynchronous
        call.enqueue(new Callback<MovieResponse>() {

            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.i("reach", "aa raha h");
                MovieResponse movieResponse = response.body();
                final ArrayList<Moviedb> movieArrayList = movieResponse.getMovieList();

                String t = movieArrayList.get(0).title;

                Log.i("mytag", t);
                onDownloadComplete(movieArrayList);
                MovieDatabase db = MovieDatabase.getInstancePopular(getContext());
                final MovieDao movieDao = db.movieDao();
                new AsyncTask<Void, Void, Void>() {
                    protected Void doInBackground(Void... voids) {
                      movieDao.insertAll(movieArrayList.toArray( new Moviedb[movieArrayList.size()]));
                        return null;

                    }

                }.execute();
            }







            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });




    }



    public void onDownloadComplete(ArrayList<Moviedb> movies) {
        this.movies.clear();
        this.movies.addAll(movies);

        mAdapter.notifyDataSetChanged();

    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_switch)
        {
            if(icon==0){
                icon = 1;
                item.setIcon(getResources().getDrawable(R.drawable.ic_list));
                mAdapter2 = new RecyclerAdapter2(getContext(), movies, new RecyclerAdapter2.MoviesClickListener() {
                    public void onItemClick(View view, int position) {
                        if(mListener != null){
                            mListener.onListItemClicked(movies.get(position));
                        }
                    }



                });
                mRecyclerView.setAdapter(mAdapter2);
                mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
                mRecyclerView.removeItemDecoration(decoration);
              //  mRecyclerView.setItemAnimator(new SlideInLeftAnimator());
                //mAdapter2.notifyAddEach();
                //mAdapter2.notifyRemoveEach();


            }else if(icon==1) {
                icon = 0;
                item.setIcon(getResources().getDrawable(R.drawable.ic_grid));
                mAdapter = new RecyclerAdapter(getContext(), movies, new RecyclerAdapter.MoviesClickListener() {
                    public void onItemClick(View view, int position) {
                        if (mListener != null) {
                            mListener.onListItemClicked(movies.get(position));
                        }
                    }


                });
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mRecyclerView.addItemDecoration(decoration);

            }


            return true;
        }
        return super.onOptionsItemSelected(item);
        }

    }






interface MoviesFragmentListItemClick{
    void onListItemClicked(Moviedb m);
}

