package com.example.avma1997.tmdb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Avma1997 on 7/27/2017.
 */

public class RecyclerAdapter8 extends RecyclerView.Adapter<RecyclerAdapter8.MovieViewHolder8> {

    private Context mContext;

    private MoviesClickListener mListener;
    private ArrayList<TV> mTVShows;

    public interface MoviesClickListener {
        void onItemClick(View view, int position);
    }


    public RecyclerAdapter8(Context context,ArrayList<TV> tvShows, MoviesClickListener listener) {
        mContext = context;
        mTVShows=tvShows;
        mListener = listener;
    }

    public MovieViewHolder8 onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_item_grid, parent, false);
        return new MovieViewHolder8(itemView, mListener);
    }

    public void onBindViewHolder(MovieViewHolder8 holder, int position) {
        TV tv = mTVShows.get(position);
        holder.titleTextView.setText(tv.name);
        Picasso.with(mContext).load("http://image.tmdb.org/t/p/w342" + tv.poster_path).into(holder.posterImageView);


    }

    public int getItemCount() {
        return mTVShows.size();
    }

    public static class MovieViewHolder8 extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleTextView;
        ImageView posterImageView;
        MoviesClickListener mMoviesClickListener;

        public MovieViewHolder8(View itemView, MoviesClickListener listener) {
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

}
