package com.loboda.james.pocketmoviesearch.views;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubePlayerView;
import com.loboda.james.pocketmoviesearch.R;
import com.loboda.james.pocketmoviesearch.activity.MainActivity;
import com.loboda.james.pocketmoviesearch.adapter.genres.GenresAdapter;
import com.loboda.james.pocketmoviesearch.adapter.movies.MoviesAdapter;
import com.loboda.james.pocketmoviesearch.clicks.Clicks;
import com.loboda.james.pocketmoviesearch.colors.ColorChoices;
import com.loboda.james.pocketmoviesearch.fonts.Fonts;
import com.loboda.james.pocketmoviesearch.lists.Lists;
import com.loboda.james.pocketmoviesearch.trailer.YouTubeTrailer;
import com.loboda.james.pocketmoviesearch.util.Util;

/**
 * Created by Twaltex on 7/8/2017.
 */

public class Views {

    // region Variables

    // Home Layout
    public static LinearLayout layoutHome;
    public static LinearLayout layoutEmptyRecyclerView;
    public static LinearLayout topHomeLayout, bottomHomeLayout, centerHomeLayout;
    public static LinearLayout holderButtonListLayoutType, holderButtonRefreshList;
    public static LinearLayout holderSwitchUpcoming;
    public static LinearLayout holderRecyclerViewGenres;
    public static TextView textMovieSortType;
    public static TextView textUpcomingThisMonth, getTextUpcomingNextMonth;
    public static Switch switchUpcoming;
    public static RecyclerView recyclerViewMovies, recyclerViewGenres;
    public static MoviesAdapter moviesAdapter;
    public static GenresAdapter genresAdapter;
    public static ImageView buttonListLayoutType, buttonRefreshList;
    public static LinearLayout buttonMovieSortTypePopular, buttonMovieSortTypeTop,
            buttonMovieSortTypeNowPlaying, buttonMovieSortTypeUpcoming, buttonSettings;
    public static TextView textButtonMovieSortTypePopular, textButtonMovieSortTypeTop,
            textButtonMovieSortTypeNowPlaying, textButtonMovieSortTypeUpcoming, textButtonSettings;
    public static ImageView iconButtonMovieSortTypePopular, iconButtonMovieSortTypeTop,
            iconButtonMovieSortTypeNowPlaying, iconButtonMovieSortTypeUpcoming, iconButtonSettings;
    public static SwipeRefreshLayout refreshLayoutRecyclerView;

    // Detailed Movie Layout
    public static LinearLayout layoutDetailedMovie;
    public static FrameLayout holderPosterDetailedMovie;
    public static TextView textTitleDetailedMovie, textDescriptionDetailedMovie;
    public static TextView textReleaseDateDetailedMovie, textVoteCountDetailedMovie, textRatingDetailedMovie;
    public static ImageView buttonCloseDetailedMovieLayout, buttonPlayTrailerDetailedMovie;
    public static ImageView posterDetailedMovie;
    public static YouTubePlayerView youTubePlayerView;

    // Settings
    public static LinearLayout layoutSettings;
    public static TextView settingsTitle, settingsSubtitle, settingsApiTitle, settingsApiDescription;
    public static ImageView buttonCloseSettings;

    // Toast
    public static Toast toast;
    public static View toast_view;
    public static TextView toast_textview;

    // Other
    public static Activity contextActivity = MainActivity.mainActivity;
    public static int listLayoutType = 1; // 1 = linear, 2 = grid
    public static int screenLayout = 1; // 1 = home, 2 = detailed movie, 3 = settings

    // endregion

    private static void setToast() {

        LayoutInflater inflater = contextActivity.getLayoutInflater();
        toast_view = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) contextActivity.findViewById(R.id.layout_toast_holder));

        toast_textview = toast_view.findViewById(R.id.textview_toast);
        toast_textview.setTypeface(Fonts.font_roboto_light);

        toast = new Toast(contextActivity);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setView(toast_view);
        toast.setDuration(Toast.LENGTH_SHORT);

    }

    private static void setFonts() {

        Fonts.setFonts();

    }

    public static void setViews() {

        setFonts();
        setToast();
        setLayoutHome();
        setLayoutDetailedMovie();
        setLayoutSettings();

        // check movie sort type
        checkMovieSortType();

        // clicks
        setClicks();

        // region refresh layout for recyclerview
        refreshLayoutRecyclerView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Util.refreshMoviesList();

                // no longer refresh on finished
                refreshLayoutRecyclerView.setRefreshing(false);

            }
        });
        // endregion

    }

    public static void setLayoutHome() {

        layoutHome = contextActivity.findViewById(R.id.layout_home);

        setHomeTopLayout();
        setHomeCenterLayout();
        setHomeBottomLayout();

    }

    public static void setLayoutDetailedMovie() {

        layoutDetailedMovie = contextActivity.findViewById(R.id.layout_detailed_movie);

        holderPosterDetailedMovie = contextActivity.findViewById(R.id.holder_poster_detailed_movie);
        youTubePlayerView = contextActivity.findViewById(R.id.youtube_player_detailed_movie);
        textTitleDetailedMovie = contextActivity.findViewById(R.id.text_title_detailed_movie);
        textDescriptionDetailedMovie = contextActivity.findViewById(R.id.text_description_detailed_movie);
        textReleaseDateDetailedMovie = contextActivity.findViewById(R.id.text_release_date_detailed_movie);
        textVoteCountDetailedMovie = contextActivity.findViewById(R.id.text_vote_count_detailed_movie);
        textRatingDetailedMovie = contextActivity.findViewById(R.id.text_rating_detailed_movie);
        buttonCloseDetailedMovieLayout = contextActivity.findViewById(R.id.button_close_detailed_movie_layout);
        buttonPlayTrailerDetailedMovie = contextActivity.findViewById(R.id.button_play_trailer_detailed_movie);
        posterDetailedMovie = contextActivity.findViewById(R.id.poster_detailed_movie);

        // fonts
        textTitleDetailedMovie.setTypeface(Fonts.font_roboto_light);
        textDescriptionDetailedMovie.setTypeface(Fonts.font_roboto_light);
        textReleaseDateDetailedMovie.setTypeface(Fonts.font_roboto_light);
        textVoteCountDetailedMovie.setTypeface(Fonts.font_roboto_light);
        textRatingDetailedMovie.setTypeface(Fonts.font_roboto_light);

        // set YouTube Trailer
        YouTubeTrailer.setYouTubeTrailer();


    }

    public static void setLayoutSettings(){

        layoutSettings = contextActivity.findViewById(R.id.layout_settings);
        settingsTitle = contextActivity.findViewById(R.id.settings_title);
        settingsSubtitle = contextActivity.findViewById(R.id.settings_subtitle);
        settingsApiTitle = contextActivity.findViewById(R.id.settings_api_title);
        settingsApiDescription = contextActivity.findViewById(R.id.settings_api_description);
        buttonCloseSettings = contextActivity.findViewById(R.id.button_close_settings);

        // fonts
        settingsTitle.setTypeface(Fonts.font_roboto_light);
        settingsSubtitle.setTypeface(Fonts.font_roboto_light);
        settingsApiTitle.setTypeface(Fonts.font_roboto_light);
        settingsApiDescription.setTypeface(Fonts.font_roboto_light);

    }

    private static void setClicks() {

        // home
        Clicks.onClickTextMovieSortType();
        Clicks.onClickButtonRefreshList();
        Clicks.onClickButtonListLayoutType();
        Clicks.onClickButtonMovieSortTypePopular();
        Clicks.onClickButtonMovieSortTypeTop();
        Clicks.onClickButtonMovieSortTypeNowPlaying();
        Clicks.onClickButtonMovieSortTypeUpcoming();
        Clicks.onClickSwitchUpcoming();
        Clicks.onClickTextThisMonth();
        Clicks.onClickTextNextMonth();

        // detailed movie
        Clicks.onClickButtonPlayTrailer();
        Clicks.onClickButtonCloseDetailedMovieLayout();

        // settings
        Clicks.onClickButtonSettings();
        Clicks.onClickButtonCloseSettingsLayout();

    }

    public static void setHomeTopLayout() {

        topHomeLayout = contextActivity.findViewById(R.id.top_home_layout);
        holderButtonListLayoutType = contextActivity.findViewById(R.id.holder_button_list_layout_type);
        holderButtonRefreshList = contextActivity.findViewById(R.id.holder_button_refresh_list);
        buttonListLayoutType = contextActivity.findViewById(R.id.button_list_layout_type);
        buttonRefreshList = contextActivity.findViewById(R.id.button_refresh_list);
        textMovieSortType = contextActivity.findViewById(R.id.text_movie_sort_type);

        // fonts
        textMovieSortType.setTypeface(Fonts.font_roboto_light);

    }

    public static void setHomeBottomLayout() {

        // Bottom button controls
        bottomHomeLayout = contextActivity.findViewById(R.id.bottom_home_layout);
        buttonMovieSortTypePopular = contextActivity.findViewById(R.id.button_movie_sort_popular);
        buttonMovieSortTypeTop = contextActivity.findViewById(R.id.button_movie_sort_top);
        buttonMovieSortTypeNowPlaying = contextActivity.findViewById(R.id.button_movie_sort_now_playing);
        buttonMovieSortTypeUpcoming = contextActivity.findViewById(R.id.button_movie_sort_upcoming);
        buttonSettings = contextActivity.findViewById(R.id.button_settings);

        // Bottom button controls icon
        iconButtonMovieSortTypePopular = contextActivity.findViewById(R.id.icon_button_movie_sort_popular);
        iconButtonMovieSortTypeTop = contextActivity.findViewById(R.id.icon_button_movie_sort_top);
        iconButtonMovieSortTypeNowPlaying = contextActivity.findViewById(R.id.icon_button_movie_sort_now_playing);
        iconButtonMovieSortTypeUpcoming = contextActivity.findViewById(R.id.icon_button_movie_sort_upcoming);
        iconButtonSettings = contextActivity.findViewById(R.id.icon_button_settings);

        // Bottom button controls text
        textButtonMovieSortTypePopular = contextActivity.findViewById(R.id.text_button_movie_sort_popular);
        textButtonMovieSortTypeTop = contextActivity.findViewById(R.id.text_button_movie_sort_top);
        textButtonMovieSortTypeNowPlaying = contextActivity.findViewById(R.id.text_button_movie_sort_now_playing);
        textButtonMovieSortTypeUpcoming = contextActivity.findViewById(R.id.text_button_movie_sort_upcoming);
        textButtonSettings = contextActivity.findViewById(R.id.text_button_settings);

        // fonts
        textButtonMovieSortTypePopular.setTypeface(Fonts.font_roboto_light);
        textButtonMovieSortTypeTop.setTypeface(Fonts.font_roboto_light);
        textButtonMovieSortTypeNowPlaying.setTypeface(Fonts.font_roboto_light);
        textButtonMovieSortTypeUpcoming.setTypeface(Fonts.font_roboto_light);
        textButtonSettings.setTypeface(Fonts.font_roboto_light);

    }

    public static void setHomeCenterLayout() {

        centerHomeLayout = contextActivity.findViewById(R.id.center_home_layout);

        // switch setup
        holderSwitchUpcoming = contextActivity.findViewById(R.id.holder_upcoming_switch);
        switchUpcoming = contextActivity.findViewById(R.id.switch_upcoming);
        textUpcomingThisMonth = contextActivity.findViewById(R.id.text_upcoming_this_month);
        getTextUpcomingNextMonth = contextActivity.findViewById(R.id.text_upcoming_next_month);

        // list
        setHomeRecyclerViews();


    }

    public static void setHomeRecyclerViews() {

        setRecyclerViewMovies();
        setRecyclerViewGenres();

    }

    public static void setRecyclerViewMovies() {


        layoutEmptyRecyclerView = contextActivity.findViewById(R.id.layout_empty_recyclerview);
        refreshLayoutRecyclerView = contextActivity.findViewById(R.id.refresh_layout_movies_recycler_view);
        recyclerViewMovies = contextActivity.findViewById(R.id.movies_recycler_view);
        setRecyclerMoviesAdapterLayout();

    }

    public static void setRecyclerViewGenres() {

        holderRecyclerViewGenres = contextActivity.findViewById(R.id.holder_genres_recycler_view);
        recyclerViewGenres = contextActivity.findViewById(R.id.genres_recycler_view);
        setRecyclerGenresAdapterLayout();

    }


    /**
     * Check if RecyclerView is empty
     * yes: show empty view
     * no: show RecyclerView
     */
    public static void checkRecyclerMoviesViewEmpty() {

        // instead of RecyclerView: hide RefreshLayout that is holding it

        if (moviesAdapter.getItemCount() == 0) {

            refreshLayoutRecyclerView.setVisibility(View.GONE);
            layoutEmptyRecyclerView.setVisibility(View.VISIBLE);

        } else {

            layoutEmptyRecyclerView.setVisibility(View.GONE);
            refreshLayoutRecyclerView.setVisibility(View.VISIBLE);

        }

    }

    public static void checkRecyclerGenresViewEmpty() {

        // instead of RecyclerView: hide Holder that is holding it

        if (genresAdapter.getItemCount() == 0) {

            holderRecyclerViewGenres.setVisibility(View.GONE);

        } else {

            holderRecyclerViewGenres.setVisibility(View.VISIBLE);

        }

    }

    public static void setRecyclerMoviesAdapterLayout() {

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                RecyclerView.LayoutManager layoutManager;

                if (listLayoutType == 1) {

                    layoutManager = new LinearLayoutManager(contextActivity);

                    Views.buttonListLayoutType.setImageResource(R.drawable.ic_view_module_black_48dp);
                    recyclerViewMovies.setLayoutManager(layoutManager);
                    moviesAdapter = new MoviesAdapter(Lists.movies, R.layout.list_item_movie_linear, contextActivity);
                    recyclerViewMovies.setAdapter(moviesAdapter);

                } else {

                    layoutManager = new GridLayoutManager(contextActivity, 2);

                    Views.buttonListLayoutType.setImageResource(R.drawable.ic_view_stream_black_48dp);
                    recyclerViewMovies.setLayoutManager(layoutManager);
                    moviesAdapter = new MoviesAdapter(Lists.movies, R.layout.list_item_movie_grid, contextActivity);
                    recyclerViewMovies.setAdapter(moviesAdapter);

                }

                // check if adapter is empty
                checkRecyclerMoviesViewEmpty();

            }
        };

        handler.post(runnable);

    }

    public static void setRecyclerGenresAdapterLayout() {

        RecyclerView.LayoutManager layoutManager;

        layoutManager = new LinearLayoutManager(contextActivity, LinearLayoutManager.HORIZONTAL, false);

        recyclerViewGenres.setLayoutManager(layoutManager);
        genresAdapter = new GenresAdapter(Lists.genres, R.layout.list_item_genre, contextActivity);
        recyclerViewGenres.setAdapter(genresAdapter);

        // check if adapter is empty
        checkRecyclerGenresViewEmpty();

    }

    /**
     * Check how the movies are being sorted
     * 1) popular 2) top 3) latest 4) upcoming
     */
    public static void checkMovieSortType() {

        switch (Util.movieSortType) {
            case 1:
                textMovieSortType.setText("Popular");
                changeMovieSortTypeButtonBackground(iconButtonMovieSortTypePopular, textButtonMovieSortTypePopular);
                break;
            case 2:
                textMovieSortType.setText("Top Rated");
                changeMovieSortTypeButtonBackground(iconButtonMovieSortTypeTop, textButtonMovieSortTypeTop);
                break;
            case 3:
                textMovieSortType.setText("Now Playing");
                changeMovieSortTypeButtonBackground(iconButtonMovieSortTypeNowPlaying, textButtonMovieSortTypeNowPlaying);
                break;
            case 4:
                textMovieSortType.setText("Coming Soon");
                changeMovieSortTypeButtonBackground(iconButtonMovieSortTypeUpcoming, textButtonMovieSortTypeUpcoming);
                break;
        }

    }

    /**
     * Change the color of Movie Sort Type Buttons : when selected button
     *
     * @param icon
     * @param text
     */
    public static void changeMovieSortTypeButtonBackground(ImageView icon, TextView text) {

        // reset all buttons to original background
        iconButtonMovieSortTypePopular.setColorFilter(null);
        iconButtonMovieSortTypeTop.setColorFilter(null);
        iconButtonMovieSortTypeNowPlaying.setColorFilter(null);
        iconButtonMovieSortTypeUpcoming.setColorFilter(null);
        iconButtonSettings.setColorFilter(null);


        // reset all buttons to original text color
        textButtonMovieSortTypePopular.setTextColor(ColorChoices.color_dark_gray);
        textButtonMovieSortTypeTop.setTextColor(ColorChoices.color_dark_gray);
        textButtonMovieSortTypeNowPlaying.setTextColor(ColorChoices.color_dark_gray);
        textButtonMovieSortTypeUpcoming.setTextColor(ColorChoices.color_dark_gray);
        textButtonSettings.setTextColor(ColorChoices.color_dark_gray);

        // set selected button to a new background
        icon.setColorFilter(ColorChoices.color_red);
        text.setTextColor(ColorChoices.color_red);

    }

    public static void showHomeLayout() {

        if (screenLayout == 1) {
            // do nothing, already at home screen
        } else {

            screenLayout = 1;
            layoutDetailedMovie.setVisibility(View.GONE);
            layoutSettings.setVisibility(View.GONE);
            layoutHome.setVisibility(View.VISIBLE);

        }

    }

    public static void showDetailedMovieLayout() {

        if (screenLayout == 2) {
            // do nothing, already at detailed movie screen
        } else {

            screenLayout = 2;
            layoutHome.setVisibility(View.GONE);
            layoutSettings.setVisibility(View.GONE);
            layoutDetailedMovie.setVisibility(View.VISIBLE);

        }
    }

    public static void showSettingsLayout(){

        if (screenLayout == 3) {
            // do nothing, already at detailed movie screen
        } else {

            screenLayout = 3;
            layoutHome.setVisibility(View.GONE);
            layoutDetailedMovie.setVisibility(View.GONE);
            layoutSettings.setVisibility(View.VISIBLE);

        }

    }

}
