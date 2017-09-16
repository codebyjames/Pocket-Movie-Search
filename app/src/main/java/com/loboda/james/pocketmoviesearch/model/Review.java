package com.loboda.james.pocketmoviesearch.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Twaltex on 7/12/2017.
 */

public class Review {

    @SerializedName("id")
    private String id;

    @SerializedName("author")
    private String author;

    @SerializedName("content")
    private String content;

    @SerializedName("url")
    private String url;

    String baseImageUrl = "https://image.tmdb.org/t/p/w500";
}
