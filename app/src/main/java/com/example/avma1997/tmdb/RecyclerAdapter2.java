package com.example.avma1997.tmdb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Avma1997 on 7/21/2017.
 */

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.MovieViewHolder2> {

    private Context mContext;

    private MoviesClickListener mListener;
    private ArrayList<Moviedb> mMovies;

    public interface MoviesClickListener {
        void onItemClick(View view, int position);
    }


    public RecyclerAdapter2(Context context,ArrayList<Moviedb> movies, MoviesClickListener listener) {
        mContext = context;
        mMovies=movies;
        mListener = listener;
    }

    public MovieViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_item_grid, parent, false);
        return new MovieViewHolder2(itemView, mListener);
    }

    public void onBindViewHolder(MovieViewHolder2 holder, int position) {
        Moviedb movie = mMovies.get(position);
        holder.titleTextView.setText(movie.title);
        Picasso.with(mContext).load("http://image.tmdb.org/t/p/w342" + movie.poster_path).into(holder.posterImageView);


    }

    public int getItemCount() {
        return mMovies.size();
    }

    public static class MovieViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleTextView;
        ImageView posterImageView;
        MoviesClickListener mMoviesClickListener;

        public MovieViewHolder2(View itemView, MoviesClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            mMoviesClickListener = listener;
            titleTextView = (TextView) itemView.findViewById(R.id.title_text_grid);

            posterImageView = (ImageView) itemView.findViewById(R.id.poster_image_grid);

        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                if (id == R.id.movie_grid_layout) {
                    mMoviesClickListener.onItemClick(view, position);
                }

            }


        }
    }
    public void notifyRemoveEach(){
        for (int i = 0; i < mMovies.size(); i++) {
            notifyItemRemoved(i);
        }
    }

    public void notifyAddEach(){
        for (int i = 0; i < mMovies.size(); i++) {
            notifyItemInserted(i);
        }
    }
}
