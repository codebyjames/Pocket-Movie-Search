package com.loboda.james.pocketmoviesearch.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Twaltex on 7/7/2017.
 */

public class Genre {

    // region How JSON items are described from website

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    // endregion

    public Genre(int id, String name) {

        this.id = id;
        this.name = name;

    }

    // region METHODS (GET/SET)

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

    // endregion
}
