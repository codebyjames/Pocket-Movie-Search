package com.loboda.james.pocketmoviesearch.detailed_movie;

import com.bumptech.glide.Glide;
import com.loboda.james.pocketmoviesearch.R;
import com.loboda.james.pocketmoviesearch.activity.MainActivity;
import com.loboda.james.pocketmoviesearch.lists.Lists;
import com.loboda.james.pocketmoviesearch.util.Util;
import com.loboda.james.pocketmoviesearch.views.Views;

/**
 * Created by Twaltex on 7/13/2017.
 */

public abstract class DetailedMovie {

    public static int selectedMovieIndex;
    public static String title, description, releaseDate, rating, voteCount, imagePath;
    public static String trailerKeyYouTube;

    /**
     * Set movie details, by using index (position)
     * @param movieIndex
     */
    public static void setMovieDetails(int movieIndex){

        selectedMovieIndex = movieIndex;

        title = Lists.movies.get(selectedMovieIndex).getTitle();
        description = Lists.movies.get(selectedMovieIndex).getOverview();
        releaseDate = Util.formatReleaseDate(Lists.movies.get(selectedMovieIndex).getReleaseDate());
        rating = Lists.movies.get(selectedMovieIndex).getVoteAverage().toString();
        voteCount = Util.formatNumberWithSuffix(Lists.movies.get(selectedMovieIndex).getVoteCount());
        imagePath = Lists.movies.get(selectedMovieIndex).getPosterPath();

        // Set Views
        Views.textTitleDetailedMovie.setText(Util.trimFrontWhitespaces(title));
        Views.textDescriptionDetailedMovie.setText(Util.trimFrontWhitespaces(description));
        Views.textReleaseDateDetailedMovie.setText(Util.trimFrontWhitespaces(releaseDate));
        Views.textRatingDetailedMovie.setText(Util.trimFrontWhitespaces(rating));
        Views.textVoteCountDetailedMovie.setText(Util.trimFrontWhitespaces(voteCount));

        // Set Poster Image (Glide or Picasso)
        Glide.with(MainActivity.mainActivity)
                .load(imagePath)
                .placeholder(R.drawable.transparent_item)
                .into(Views.posterDetailedMovie);

    }

    /**
     * Get Trailer Key
     * @return
     */
    public static String getTrailerKeyYouTube(){

        trailerKeyYouTube = Lists.movies.get(selectedMovieIndex).getYoutubeKey();

        return trailerKeyYouTube;
    }

}
