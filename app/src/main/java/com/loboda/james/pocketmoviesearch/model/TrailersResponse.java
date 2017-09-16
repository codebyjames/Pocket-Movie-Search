package com.loboda.james.pocketmoviesearch.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Twaltex on 7/7/2017.
 */

public class TrailersResponse {

    @SerializedName("id")
    private int id;

    @SerializedName("results")
    private List<Trailer> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Trailer> getResults() {
        return results;
    }

    public void setResults(List<Trailer> results) {
        this.results = results;
    }
}
