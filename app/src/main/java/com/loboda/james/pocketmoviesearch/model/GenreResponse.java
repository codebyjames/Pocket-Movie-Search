package com.loboda.james.pocketmoviesearch.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Twaltex on 7/7/2017.
 */

public class GenreResponse {

    @SerializedName("genres")
    private List<Genre> results;

    public List<Genre> getResults() {
        return results;
    }

    public void setResults(List<Genre> results) {
        this.results = results;
    }
}
