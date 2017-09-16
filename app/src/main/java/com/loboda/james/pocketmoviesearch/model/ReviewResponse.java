package com.loboda.james.pocketmoviesearch.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Twaltex on 7/12/2017.
 */

public class ReviewResponse {

    @SerializedName("page")
    private int page;
    @SerializedName("results") // all movies
    private List<Movie> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

}
