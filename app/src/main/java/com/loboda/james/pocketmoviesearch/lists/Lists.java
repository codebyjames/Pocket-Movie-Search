package com.loboda.james.pocketmoviesearch.lists;


import com.loboda.james.pocketmoviesearch.model.Genre;
import com.loboda.james.pocketmoviesearch.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Twaltex on 7/8/2017.
 */

public class Lists {

    public static List<Movie> movies;
    public static List<Genre> genres;

    public static void initializeLists(){

        movies = new ArrayList<>();
        genres = new ArrayList<>();
    }

    /**
     * If cannot get data from API, then use default genres (id, name)
     * <>got it from the website</>
     */
    public static void setDefaultGenres(){

        genres = new ArrayList<>();
        addAllDefaultGenres();

    }

    private static void addAllDefaultGenres(){

        addSingleDefaultGenre(0, "All");
        addSingleDefaultGenre(28, "Action");
        addSingleDefaultGenre(12, "Adventure");
        addSingleDefaultGenre(16, "Animation");
        addSingleDefaultGenre(35, "Comedy");
        addSingleDefaultGenre(80, "Crime");
        addSingleDefaultGenre(99, "Documentary");
        addSingleDefaultGenre(18, "Drama");
        addSingleDefaultGenre(10751, "Family");
        addSingleDefaultGenre(14, "Fantasy");
        addSingleDefaultGenre(36, "History");
        addSingleDefaultGenre(27, "Horror");
        addSingleDefaultGenre(10402, "Music");
        addSingleDefaultGenre(9648, "Mystery");
        addSingleDefaultGenre(10749, "Romance");
        addSingleDefaultGenre(878, "Science Fiction");
        addSingleDefaultGenre(10770, "TV Movie");
        addSingleDefaultGenre(53, "Thriller");
        addSingleDefaultGenre(10752, "War");
        addSingleDefaultGenre(37, "Western");

    }

    private static void addSingleDefaultGenre(int id, String name){

        genres.add(new Genre(id, name));

    }

}
