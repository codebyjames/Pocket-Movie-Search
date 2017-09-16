package com.loboda.james.pocketmoviesearch.adapter.movies;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.loboda.james.pocketmoviesearch.R;
import com.loboda.james.pocketmoviesearch.api.FetchData;
import com.loboda.james.pocketmoviesearch.detailed_movie.DetailedMovie;
import com.loboda.james.pocketmoviesearch.lists.Lists;
import com.loboda.james.pocketmoviesearch.model.Movie;
import com.loboda.james.pocketmoviesearch.util.Util;
import com.loboda.james.pocketmoviesearch.views.Views;

import java.util.List;

/**
 * Created by Twaltex on 7/7/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> movies;
    private int rowLayout;
    private Context context;

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {

        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;

    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {

        if (Views.listLayoutType == 1) {
            // linear
            Util.lastKnowAdapterItemPositionLinear = position;

        } else {
            // grid
            Util.lastKnowAdapterItemPositionGrid = position;

        }

        // adjust position for when switching between linear & grid
        Util.adjustLastKnownPositions();

        // process UI & fetch data if needed
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                try {

                    if (!Lists.movies.isEmpty() || Lists.movies == null) {

                        if (position == (Lists.movies.size() - 1)) {

                            // load more data (popular & top)
                            if (Util.movieSortType == 3 || Util.movieSortType == 4) {
                                // do nothing
                            } else {

                                // only fetch more items for popular && top rated
                                if (Util.currentGenreSelectedId == 0) {

                                    // Genre (All) selected
                                    FetchData.startMovieDownloadService(FetchData.TYPE_DOWNLOAD_SERVICE_NEXT_PAGE, 0);

                                }

                            }

                        }

                    }

                    // manipulate data
                    processBindViewHolderData(holder, position);

                } catch (Exception ex) {

                    // encountered problem with notify data change or scrolling recyclerview
                    ex.printStackTrace();

                }

            }
        };

        handler.post(runnable);

    }

    private void processBindViewHolderData(final MovieViewHolder holder, final int position){

//        String textTitle = movies.get(position).getTitle();
        String textReleaseDare = Util.formatReleaseDate(movies.get(position).getReleaseDate());
//        String textDescription = movies.get(position).getOverview();
        String textRating = movies.get(position).getVoteAverage().toString();
        String textVoteCount = Util.formatNumberWithSuffix(movies.get(position).getVoteCount());


        holder.releaseDate.setText(textReleaseDare);
        holder.rating.setText(textRating);
        holder.voteCount.setText(textVoteCount);

        // Set Image (Glide or Picasso)
        String imagePath = movies.get(position).getPosterPath();

        Glide.with(context)
                .load(imagePath)
                .placeholder(R.drawable.empty)
                .into(holder.posterImage);

        // Show Detailed Movie Layout
        holder.posterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Set Detailed Movie by position
                DetailedMovie.setMovieDetails(position);

                // get trailer for current movie
                getTrailerForCurrentMovie(position);

                Views.showDetailedMovieLayout();

            }
        });


    }

    private void getTrailerForCurrentMovie(int position){

        FetchData.startMovieTrailerDownloadService(Lists.movies.get(position), Lists.movies.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


}
