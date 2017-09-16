package com.loboda.james.pocketmoviesearch.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Twaltex on 7/7/2017.
 */

public class Detail {

    // region How JSON items are described from website

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    // endregion


    String baseImageUrl = "https://image.tmdb.org/t/p/w500";

    public Detail(int id, String name, String posterPath, String backdropPath) {

        this.id = id;
        this.name = name;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;

    }

    // region METHODS (GET/SET)

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w500" + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    // endregion
}
