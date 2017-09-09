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
 * Created by Avma1997 on 7/26/2017.
 */

public class RecyclerAdapter6 extends RecyclerView.Adapter<RecyclerAdapter6.MovieViewHolder6> {

    private Context mContext;

    private MoviesClickListener mListener;
    private ArrayList<Moviedb> mMovies;

    public interface MoviesClickListener {
        void onItemClick(View view, int position);
    }


    public RecyclerAdapter6(Context context, ArrayList<Moviedb> movies, MoviesClickListener listener) {
        mContext = context;
        mMovies = movies;
        mListener = listener;
    }

    public MovieViewHolder6 onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.similarmovies, parent, false);
        return new MovieViewHolder6(itemView, mListener);
    }

    public void onBindViewHolder(MovieViewHolder6 holder, int position) {
        Moviedb movie = mMovies.get(position);
        Picasso.with(mContext).load("http://image.tmdb.org/t/p/w342"+movie.poster_path).into(holder.posterimage);
if(movie.title.length()>15)
{holder.titleTextView.setText(movie.title.substring(0,10)+"...");}
else
{ holder.titleTextView.setText(movie.title);}
        holder.yearTextView.setText(movie.release_date.substring(0,4));


    }

    public int getItemCount() {
        return mMovies.size();
    }

    public static class MovieViewHolder6 extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView posterimage;
        TextView titleTextView;
        TextView yearTextView;


        MoviesClickListener mVideosClickListener;

        public MovieViewHolder6(View itemView, MoviesClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            mVideosClickListener = listener;
           posterimage=(ImageView)itemView.findViewById(R.id.similarimage);
            titleTextView=(TextView)itemView.findViewById(R.id.similartitle);
            yearTextView=(TextView)itemView.findViewById(R.id.similaryear);


        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                if (id == R.id.cast_layout) {
                    mVideosClickListener.onItemClick(view, position);
                }

            }


        }
    }
}

