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
 * Created by Avma1997 on 7/23/2017.
 */

public class RecyclerAdapter3 extends RecyclerView.Adapter<RecyclerAdapter3.MovieViewHolder3> {

    private Context mContext;

    private MoviesClickListener mListener;
    private ArrayList<Videos> mVideos;

    public interface MoviesClickListener {
        void onItemClick(View view, int position);
    }


    public RecyclerAdapter3(Context context, ArrayList<Videos> videos, MoviesClickListener listener) {
        mContext = context;
        mVideos = videos;
        mListener = listener;
    }

    public MovieViewHolder3 onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.view_video, parent, false);
        return new MovieViewHolder3(itemView, mListener);
    }

    public void onBindViewHolder(MovieViewHolder3 holder, int position) {
        Videos video = mVideos.get(position);
        holder.titleTextView.setText(video.name);
        Picasso.with(mContext).load("https://img.youtube.com/vi/" + video.key + "/0.jpg").into(holder.videoImageView);



    }

    public int getItemCount() {
        return mVideos.size();
    }

    public static class MovieViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleTextView;
        ImageView videoImageView;
        MoviesClickListener mVideosClickListener;

        public MovieViewHolder3(View itemView, MoviesClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            mVideosClickListener = listener;
            titleTextView = (TextView) itemView.findViewById(R.id.movietv);

            videoImageView = (ImageView) itemView.findViewById(R.id.movieiv);

        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                if (id == R.id.video_layout) {
                    mVideosClickListener.onItemClick(view, position);
                }

            }


        }
    }
}
