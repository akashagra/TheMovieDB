package com.example.avma1997.tmdb;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Avma1997 on 7/25/2017.
 */

 public class ReviewResponse {

    @SerializedName("results")
    private ArrayList<Review> reviewList;

    public ArrayList<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(ArrayList<Review> reviewList) {
        this.reviewList = reviewList;
    }


}
