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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Avma1997 on 7/25/2017.
 */
public class Fragtab3 extends Fragment {
Moviedb m;
        RecyclerView mRecyclerView;
        RecyclerAdapter5 mAdapter;
        ArrayList<Review> mReviews;
        MoviesFragmentListItemClick mListener;
        int ID;

@Nullable
@Override
public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_review, container, false);
        mReviews=new ArrayList<>();
        Bundle b = getArguments();
        m=(Moviedb)b.getSerializable("obj3");
        ID=m.id;
        mRecyclerView=(RecyclerView) v.findViewById(R.id.recycler_view_review);
        mAdapter = new RecyclerAdapter5(getContext(),mReviews, new RecyclerAdapter5.MoviesClickListener() {

public void onItemClick(View view, int position) {
        if(mListener != null){

        }
        }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        fetchReviews();
        return v;

        }
public void fetchReviews()
        {   Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();


        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<ReviewResponse> call = apiInterface.getMovieReviews(ID,"69a57d4b7c3b085d539f991b8a27ebce");
        call.enqueue(new Callback<ReviewResponse>() {

@Override
public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
        Log.i("reach","aa raha h");
            ReviewResponse reviewResponse = response.body();
        ArrayList<Review> reviews= reviewResponse.getReviewList();


        onDownloadComplete(reviews);
        }

@Override
public void onFailure(Call<ReviewResponse> call, Throwable t) {

        }
        });




        }



public void onDownloadComplete(ArrayList<Review> reviews) {
        mReviews.clear();
        mReviews.addAll(reviews);

        mAdapter.notifyDataSetChanged();

        }
        }
