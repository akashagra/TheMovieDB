package com.example.avma1997.tmdb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Avma1997 on 7/23/2017.
 */

public class Fragtab2 extends Fragment {
Moviedb m;
   RecyclerView mRecyclerView;
    RecyclerAdapter4 mAdapter;
        ArrayList<Cast> mCasts;
    MoviesFragmentListItemClick mListener;
    int ID;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cast, container, false);
        mCasts=new ArrayList<>();
        Bundle b = getArguments();
        m=(Moviedb)b.getSerializable("obj2");
        ID=m.id;
        mRecyclerView=(RecyclerView) v.findViewById(R.id.recycler_view_cast);
        mAdapter = new RecyclerAdapter4(getContext(),mCasts, new RecyclerAdapter4.MoviesClickListener() {

            public void onItemClick(View view, int position) {
                if(mListener != null){

                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        fetchCasts();
        return v;

    }
    public void fetchCasts()
    {   Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    ApiInterface apiInterface = retrofit.create(ApiInterface.class);
    Call<CastResponse> call = apiInterface.getMovieCast(ID,"69a57d4b7c3b085d539f991b8a27ebce");
        call.enqueue(new Callback<CastResponse>() {

        @Override
        public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
            Log.i("reach","aa raha h");
           CastResponse castResponse = response.body();
            ArrayList<Cast> casts = castResponse.getCastList();


            onDownloadComplete(casts);
        }

        @Override
        public void onFailure(Call<CastResponse> call, Throwable t) {

        }
    });




}



    public void onDownloadComplete(ArrayList<Cast> casts) {
        mCasts.clear();
        mCasts.addAll(casts);

        mAdapter.notifyDataSetChanged();

    }
}
