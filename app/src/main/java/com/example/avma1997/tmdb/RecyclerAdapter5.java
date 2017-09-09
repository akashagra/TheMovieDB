package com.example.avma1997.tmdb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Avma1997 on 7/24/2017.
 */

public class RecyclerAdapter5 extends RecyclerView.Adapter<RecyclerAdapter5.MovieViewHolder5> {

    private Context mContext;

    private MoviesClickListener mListener;
    private ArrayList<Review> mReviews;

    public interface MoviesClickListener {
        void onItemClick(View view, int position);
    }


    public RecyclerAdapter5(Context context, ArrayList<Review> reviews, MoviesClickListener listener) {
        mContext = context;
        mReviews = reviews;
        mListener = listener;
    }

    public MovieViewHolder5 onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.review, parent, false);
        return new MovieViewHolder5(itemView, mListener);
    }

    public void onBindViewHolder(MovieViewHolder5 holder, int position) {
        Review review = mReviews.get(position);
        holder.authorTextView.setText(review.author);
        holder.contentTextView.setText(review.content);


    }

    public int getItemCount() {
        return mReviews.size();
    }

    public static class MovieViewHolder5 extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView authorTextView;
        TextView contentTextView;


        MoviesClickListener mVideosClickListener;

        public MovieViewHolder5(View itemView, MoviesClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            mVideosClickListener = listener;
            authorTextView = (TextView) itemView.findViewById(R.id.authortv);
            contentTextView=(TextView)itemView.findViewById(R.id.reviewcontenttv);


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

