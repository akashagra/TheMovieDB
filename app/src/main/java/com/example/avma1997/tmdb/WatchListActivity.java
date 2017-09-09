package com.example.avma1997.tmdb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class WatchListActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ArrayList<Moviedb> movies;
    RecyclerAdapter mAdapter;

    MoviesFragmentListItemClick mListener;

    DividerItemDecoration decoration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        movies=new ArrayList<>();
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view_watch);
        mAdapter = new RecyclerAdapter(this, movies, new RecyclerAdapter.MoviesClickListener() {
            public void onItemClick(View view, int position) {
                if (mListener != null) {
                    mListener.onListItemClicked(movies.get(position));
                }
            }


        });


        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(decoration);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        MovieDatabase db = MovieDatabase.getInstanceWatch(this);
        final MovieDao movieDao = db.movieDao();
        new AsyncTask<Void, Void, ArrayList<Moviedb>>() {

            @Override
            protected ArrayList<Moviedb> doInBackground(Void... voids) {
                ArrayList movies= (ArrayList) movieDao.loadMovies();

                return movies;
            }

            @Override
            protected void onPostExecute(ArrayList<Moviedb> movies){
                onDownloadComplete(movies);
            }
        }.execute();



    }
    public void onDownloadComplete(ArrayList<Moviedb> movies) {
        this.movies.clear();
        this.movies.addAll(movies);

        mAdapter.notifyDataSetChanged();

    }

}


