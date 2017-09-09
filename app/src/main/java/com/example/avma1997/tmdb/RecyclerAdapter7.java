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
 * Created by Avma1997 on 7/27/2017.
 */

public class RecyclerAdapter7 extends RecyclerView.Adapter<RecyclerAdapter7.MovieViewHolder7> {

    private Context mContext;
    private ArrayList<TV> mTVShows;
    private MoviesClickListener mListener;
    private String s="";
    public interface MoviesClickListener {
        void onItemClick(View view, int position);
    }





    public RecyclerAdapter7(Context context, ArrayList<TV> tvshows,MoviesClickListener listener){
        mContext = context;
        mTVShows = tvshows;
        mListener = listener;
    }


    public MovieViewHolder7 onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_item_tv,parent,false);
        return new MovieViewHolder7(itemView,mListener);
    }

    public void onBindViewHolder(MovieViewHolder7 holder, int position) {
        TV tv = mTVShows.get(position);
        int[] genreid=tv.genre_ids;
        s="";
        for(int i=0;i<genreid.length&&i<3;i++)
        {
            switch(genreid[i]) {
                case 28:
                    s = s + "Action, ";
                    break;
                case 12:
                    s = s + "Adventure, ";


                    break;
                case 16:
                    s = s + "Animation, ";
                    break;


                case 35:
                    s = s + "Comedy, ";

                    break;
                case 80:
                    s = s + "Crime, ";

                    break;


                case 99:
                    s = s + "Documentary, ";

                    break;


                case 18:
                    s = s + "Drama, ";

                    break;
                case 10751:
                    s = s + "Family, ";

                    break;
                case 14:
                    s = s + "Fantasy, ";


                    break;
                case 36:
                    s = s + "History, ";

                    break;
                case 27:
                    s = s + "Horror, ";

                    break;


                case 10402:
                    s = s + "Music, ";

                    break;
                case 9648:
                    s = s + "Mystery, ";

                    break;
                case 10749:
                    s = s + "Romance, ";

                    break;
                case 878:
                    s = s + "Science Fiction, ";

                    break;
                case 10770:
                    s = s + "T.V. Movie, ";

                    break;
                case 53:
                    s = s + "War, ";

                    break;
                case 10752:
                    s = s + "Thriller, ";

                    break;
                case 37:
                    s = s + "Western, ";

                    break;
                default:
                    s = s + "";
            }
        }





        if(tv.name.length()>30)
        {holder.titleTextView.setText(tv.name.substring(0,30)+"...");}
        else
        {holder.titleTextView.setText(tv.name);}
        holder.yearTextView.setText(tv.first_air_date.substring(0,4));
        holder.ratingTextView.setText(tv.vote_average+"");
        holder.genreTextView.setText(s.substring(0,s.length()-2));
        Log.i("poster",tv.poster_path);
        Picasso.with(mContext).load("http://image.tmdb.org/t/p/w342"+tv.poster_path).into(holder.posterImageView);


    }

    @Override
    public int getItemCount() {
        return mTVShows.size();
    }

    public static class MovieViewHolder7 extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView titleTextView;
        TextView genreTextView;
        TextView yearTextView;
        TextView ratingTextView;
        ImageView posterImageView;
        MoviesClickListener mMoviesClickListener;

        public MovieViewHolder7(View itemView,MoviesClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            mMoviesClickListener = listener;
            titleTextView = (TextView)itemView.findViewById(R.id.title_text_tv);
            genreTextView = (TextView)itemView.findViewById(R.id.genre_text_tv);
            yearTextView=(TextView)itemView.findViewById(R.id.year_text_tv);
            ratingTextView=(TextView)itemView.findViewById(R.id.rating_text_tv);
            posterImageView=(ImageView) itemView.findViewById(R.id.poster_image_tv);

        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                if(id == R.id.movie_layout){
                    mMoviesClickListener.onItemClick(view,position);
                }



























            }

        }
    }


}

