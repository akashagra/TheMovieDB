package com.example.avma1997.tmdb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Avma1997 on 7/23/2017.
 */

public class RecyclerAdapter4 extends RecyclerView.Adapter<RecyclerAdapter4.MovieViewHolder4> {

    private Context mContext;

    private MoviesClickListener mListener;
    private ArrayList<Cast> mCasts;

    public interface MoviesClickListener {
        void onItemClick(View view, int position);
    }


    public RecyclerAdapter4(Context context, ArrayList<Cast> casts, MoviesClickListener listener) {
        mContext = context;
        mCasts = casts;
        mListener = listener;
    }

    public MovieViewHolder4 onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.cast, parent, false);
        return new MovieViewHolder4(itemView, mListener);
    }

    public void onBindViewHolder(MovieViewHolder4 holder, int position) {
        Cast cast = mCasts.get(position);
        holder.nameTextView.setText(cast.name);
        holder.charTextView.setText("as  " + cast.character);
        Picasso.with(mContext).load("http://image.tmdb.org/t/p/w342"+cast.profile_path).into(holder.charImageView);


    }

    public int getItemCount() {
        return mCasts.size();
    }

    public static class MovieViewHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nameTextView;
        TextView charTextView;
        ImageView charImageView;

        MoviesClickListener mVideosClickListener;

        public MovieViewHolder4(View itemView, MoviesClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            mVideosClickListener = listener;
            nameTextView = (TextView) itemView.findViewById(R.id.castnametv);
            charTextView=(TextView)itemView.findViewById(R.id.castchartv);
            charImageView = (ImageView) itemView.findViewById(R.id.castiv);

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
