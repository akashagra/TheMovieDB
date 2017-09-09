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
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Avma1997 on 7/21/2017.
 */

 public class Fragtab1 extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView mRecyclerView2;
    RecyclerAdapter3 mAdapter;
    RecyclerAdapter6 mAdapter2;
    ArrayList<Videos> videos;
    ArrayList<Moviedb> movies;

    MoviesFragmentListItemClicked mListener;
    Moviedb m;


    void setCoursesFragmentListItemClicked(MoviesFragmentListItemClicked listener) {
        this.mListener = listener;
    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info, container, false);

        Bundle b = getArguments();


      m=(Moviedb)b.getSerializable("obj");
        TextView tv=(TextView)v.findViewById(R.id.overview);
        tv.setText(m.overview);
        TextView tv2=(TextView)v.findViewById(R.id.releasedate);
        tv2.setText("Release Date :"+m.release_date);
       videos=new ArrayList<>();
        movies=new ArrayList<>();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_info);
        mAdapter = new RecyclerAdapter3(getContext(),videos, new RecyclerAdapter3.MoviesClickListener() {

           public void onItemClick(View view, int position) {
                if(mListener != null){
                   mListener.onListItemClicked(videos.get(position));
              }
           }
      });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
   mRecyclerView.setItemAnimator(new DefaultItemAnimator());
     fetchVideos();
        mRecyclerView2 = (RecyclerView) v.findViewById(R.id.recycler_view_info2);
        mAdapter2 = new RecyclerAdapter6(getContext(),movies, new RecyclerAdapter6.MoviesClickListener() {

            public void onItemClick(View view, int position) {
                if(mListener != null){
                    mListener.onListItemSelected(movies.get(position));
                }
            }
        });
        mRecyclerView2.setAdapter(mAdapter2);
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        mRecyclerView2.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView2.setItemAnimator(new DefaultItemAnimator());
        fetchSimilarMovies();


        return v;
    }

    private void fetchSimilarMovies() {



//        String urlString = "https://codingninjas.in/api/v1/courses";
//        CoursesAsyncTask courseAsyncTask = new CoursesAsyncTask();
//        courseAsyncTask.execute(urlString);
//        courseAsyncTask.setOnDownloadCompleteListener(this);


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

int ID=m.id;
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);
            Call<MovieResponse> call = apiInterface.getSimilarMovies(ID,"69a57d4b7c3b085d539f991b8a27ebce");
            call.enqueue(new Callback<MovieResponse>() {

                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    Log.i("reach","aa raha h");
                    MovieResponse movieResponse = response.body();
                    ArrayList<Moviedb> movieArrayList = movieResponse.getMovieList();

                    String t=movieArrayList.get(0).title;

                    Log.i("mytag",t);
                    onDownloadComp(movieArrayList);
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {

                }
            });




        }



    public void onDownloadComp(ArrayList<Moviedb> movies) {
        this.movies.clear();
        this.movies.addAll(movies);

        mAdapter2.notifyDataSetChanged();





    }

    private void fetchVideos() {

////        String urlString = "https://codingninjas.in/api/v1/courses";
////        CoursesAsyncTask courseAsyncTask = new CoursesAsyncTask();
////        courseAsyncTask.execute(urlString);
////        courseAsyncTask.setOnDownloadCompleteListener(this);
//
//
       Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<VideoResponse> call = apiInterface.getMovieVideos(m.id,"69a57d4b7c3b085d539f991b8a27ebce");
     call.enqueue(new Callback<VideoResponse>() {

         @Override
         public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
             Log.i("reach","aa raha h");
             VideoResponse videoResponse = response.body();
             ArrayList<Videos> videoArrayList = videoResponse.getVideoList();

//                String t=movieArrayList.get(0).title;
//
//                Log.i("mytag",t);
             onDownloadComplete(videoArrayList);
         }

         @Override
         public void onFailure(Call<VideoResponse> call, Throwable t) {

         }


        });


//
//
}
//
//
//
    public void onDownloadComplete(ArrayList<Videos> videos) {
        this.videos.clear();
        this.videos.addAll(videos);

        mAdapter.notifyDataSetChanged();

    }
    interface MoviesFragmentListItemClicked {
        void onListItemClicked(Videos v);
        void onListItemSelected(Moviedb m);
   }


}






