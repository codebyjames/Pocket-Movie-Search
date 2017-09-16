package com.loboda.james.pocketmoviesearch.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Twaltex on 7/7/2017.
 */

public class Trailer {

    // region How JSON items are described from website

    @SerializedName("id")
    private String id;

    @SerializedName("iso_639_1")
    private String iso_639_1;

    @SerializedName("iso_3166_1")
    private String iso_3166_1;

    @SerializedName("key")
    private String youtubeKey;

    @SerializedName("name")
    private String name;

    @SerializedName("site")
    private String site;

    @SerializedName("size")
    private int size;

    @SerializedName("type")
    private String type;

    // endregion

    public Trailer(String id,String iso_639_1, String iso_3166_1, String youtubeKey, String name, String site, int size, String type) {

        this.id = id;
        this.iso_639_1 = iso_639_1;
        this.iso_3166_1 = iso_3166_1;
        this.youtubeKey = youtubeKey;
        this.name = name;
        this.site = site;
        this.size = size;
        this.type = type;

    }

    // region METHODS (GET/SET)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYoutubeKey() {
        return youtubeKey;
    }

    public void setYoutubeKey(String youtubeKey) {
        this.youtubeKey = youtubeKey;
    }


    // endregion
}
