package com.loboda.james.pocketmoviesearch.clicks;

import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;

import com.loboda.james.pocketmoviesearch.animation.AnimationObject;
import com.loboda.james.pocketmoviesearch.animation.AnimationRotate;
import com.loboda.james.pocketmoviesearch.store.AppStore;
import com.loboda.james.pocketmoviesearch.trailer.YouTubeTrailer;
import com.loboda.james.pocketmoviesearch.util.Util;
import com.loboda.james.pocketmoviesearch.views.Views;

/**
 * Created by Twaltex on 7/8/2017.
 */

public class Clicks {

    /**
     * Scroll RecyclerView to Top
     */
    public static void onClickTextMovieSortType(){

        Views.textMovieSortType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AnimationObject.stopAnimation(Views.textMovieSortType);
                AnimationObject.grow(Views.textMovieSortType, 200, 1.03f);

                if (Views.moviesAdapter.getItemCount() > 0) {

                    // scroll to top
                    Views.recyclerViewMovies.smoothScrollToPosition(0);

                    // go directly to top
//                    Views.recyclerView.scrollToPosition(0);

                }

            }
        });

    }

    /**
     * Change the structure of RecyclerView layout (grid || linear)
     */
    public static void onClickButtonListLayoutType(){

        Views.holderButtonListLayoutType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Util.changeMovieAdapterLayout();

            }
        });

    }

    /**
     * Refresh the RecyclerView
     */
    public static void onClickButtonRefreshList(){

        Views.holderButtonRefreshList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AnimationRotate.stopAnimation(Views.buttonRefreshList);

                Util.refreshMoviesList();


            }
        });

    }

    /**
     * Click buttons popular, top, now playing, upcoming
     */
    public static void onClickButtonMovieSortTypePopular(){

        Util.processButtonMovieSortTypeClick(Views.buttonMovieSortTypePopular, 1);

    }

    public static void onClickButtonMovieSortTypeTop(){

        Util.processButtonMovieSortTypeClick(Views.buttonMovieSortTypeTop, 2);

    }

    public static void onClickButtonMovieSortTypeNowPlaying(){

        Util.processButtonMovieSortTypeClick(Views.buttonMovieSortTypeNowPlaying, 3);

    }

    public static void onClickButtonMovieSortTypeUpcoming(){

        Util.processButtonMovieSortTypeClick(Views.buttonMovieSortTypeUpcoming, 4);

    }

    public static void onClickButtonSettings(){

        Views.buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AnimationObject.stopAnimation(Views.buttonSettings);
                AnimationObject.grow(Views.buttonSettings, 200, 1.03f);
                Views.changeMovieSortTypeButtonBackground(Views.iconButtonSettings, Views.textButtonSettings);

                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                        AnimationObject.stopAnimation(Views.buttonSettings);
                        Views.showSettingsLayout();

                    }
                };

                handler.postDelayed(runnable, 200);

            }
        });

    }

    /**
     * Switch Upcoming: this month / next month
     */
    public static void onClickSwitchUpcoming(){

        Views.switchUpcoming.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked) {

                    // next month chosen
                    Util.upcomingMovieSort = 2;

                } else {

                    // this month chosen
                    Util.upcomingMovieSort = 1;

                }

                Util.refreshMoviesList();

            }
        });

    }

    public static void onClickTextThisMonth(){

        Views.textUpcomingThisMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Views.switchUpcoming.setChecked(false);
            }
        });
    }

    public static void onClickTextNextMonth(){

        Views.getTextUpcomingNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Views.switchUpcoming.setChecked(true);
            }
        });
    }

    /**
     * Click play trailer button
     * <>use poster for click instead of play button</>
     */
    public static void onClickButtonPlayTrailer(){

        Views.posterDetailedMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AnimationObject.grow(Views.buttonPlayTrailerDetailedMovie, 200, 1.03f);

                Handler handler = new Handler();
                Runnable runnable  = new Runnable() {
                    @Override
                    public void run() {

                        switch (AppStore.getAppStoreType()) {
                            case 1:
                                YouTubeTrailer.showYouTubePlayer();
                                break;
                            case 2:
                                Util.showToastMessage("Device cannot play YouTube videos.");
                                break;
                            case 3:
                                Util.showToastMessage("Device cannot play YouTube videos.");
                                break;
                        }

                    }
                };

                handler.postDelayed(runnable, 200);

            }
        });

    }

    /**
     * Click close Detailed Movie Layout
     */
    public static void onClickButtonCloseDetailedMovieLayout(){

        Views.buttonCloseDetailedMovieLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AnimationRotate.rotateAnimation(Views.buttonCloseDetailedMovieLayout, 200);

                Handler handler = new Handler();
                Runnable runnable  = new Runnable() {
                    @Override
                    public void run() {

                        YouTubeTrailer.hideYouTubePlayer();
                        Views.showHomeLayout();

                    }
                };

                handler.postDelayed(runnable, 200);

            }
        });

    }

    /**
     * Click close Settings Layout
     */
    public static void onClickButtonCloseSettingsLayout(){

        Views.buttonCloseSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AnimationRotate.rotateAnimation(Views.buttonCloseSettings, 200);

                Handler handler = new Handler();
                Runnable runnable  = new Runnable() {
                    @Override
                    public void run() {

                        Views.checkMovieSortType();
                        Views.showHomeLayout();

                    }
                };

                handler.postDelayed(runnable, 200);

            }
        });

    }

}
