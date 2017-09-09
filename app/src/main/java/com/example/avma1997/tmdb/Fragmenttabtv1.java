package com.example.avma1997.tmdb;

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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Avma1997 on 7/27/2017.
 */

public class Fragmenttabtv1 extends Fragment {

    RecyclerView mRecyclerView;
    ArrayList<TV> tvshows;
    RecyclerAdapter7 mAdapter;
    RecyclerAdapter8 mAdapter2;
    MoviesFragmentListItemClick mListener;
    int icon;
    DividerItemDecoration decoration;


    void  setCoursesFragmentListItemClick(MoviesFragmentListItemClick listener){
        this.mListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tv_list, container, false);
        tvshows=new ArrayList<>();


        setHasOptionsMenu(true);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_tv);
        mAdapter = new RecyclerAdapter7(getContext(), tvshows, new RecyclerAdapter7.MoviesClickListener() {
            public void onItemClick(View view, int position) {
                if(mListener != null){
                    mListener.onListItemClicked(tvshows.get(position));
                }
            }



        });

        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        decoration=new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(decoration);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        fetchTVShows();
        return v;

    }
    private void fetchTVShows() {

//        String urlString = "https://codingninjas.in/api/v1/courses";
//        CoursesAsyncTask courseAsyncTask = new CoursesAsyncTask();
//        courseAsyncTask.execute(urlString);
//        courseAsyncTask.setOnDownloadCompleteListener(this);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<TVResponse> call = apiInterface.getPopularTVShows("69a57d4b7c3b085d539f991b8a27ebce");
        call.enqueue(new Callback<TVResponse>() {

            @Override
            public void onResponse(Call<TVResponse> call, Response<TVResponse> response) {
                Log.i("reachintv","aa raha h");
                TVResponse tvResponse = response.body();
                ArrayList<TV> tvShows = tvResponse.getTVShowsList();



                onDownloadComplete(tvShows);
            }

            @Override
            public void onFailure(Call<TVResponse> call, Throwable t) {

            }
        });




    }



    public void onDownloadComplete(ArrayList<TV> tvShows) {
        this.tvshows.clear();
        this.tvshows.addAll(tvShows);

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
                mAdapter2 = new RecyclerAdapter8(getContext(), tvshows, new RecyclerAdapter8.MoviesClickListener() {
                    public void onItemClick(View view, int position) {
                        if(mListener != null){
                            mListener.onListItemClicked(tvshows.get(position));
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
                mAdapter = new RecyclerAdapter7(getContext(), tvshows, new RecyclerAdapter7.MoviesClickListener() {
                    public void onItemClick(View view, int position) {
                        if (mListener != null) {
                            mListener.onListItemClicked(tvshows.get(position));
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
    interface MoviesFragmentListItemClick{
        void onListItemClicked(TV m);
    }

}









