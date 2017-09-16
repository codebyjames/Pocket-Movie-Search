package com.loboda.james.pocketmoviesearch.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.loboda.james.pocketmoviesearch.R;
import com.loboda.james.pocketmoviesearch.api.FetchData;
import com.loboda.james.pocketmoviesearch.broadcasts.Broadcasts;
import com.loboda.james.pocketmoviesearch.lists.Lists;
import com.loboda.james.pocketmoviesearch.store.AppStore;
import com.loboda.james.pocketmoviesearch.trailer.YouTubeTrailer;
import com.loboda.james.pocketmoviesearch.util.Util;
import com.loboda.james.pocketmoviesearch.views.Views;

public class MainActivity extends YouTubeBaseActivity {

    public static Activity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;
        setAppFullScreen();

        if (FetchData.API_KEY.isEmpty()) {
            Util.showToastMessage("Issues Fetching Data from Server");
            return;
        }

        // AppStore type
        AppStore.setAppStore();

        // set Intent Filter for Broadcast
        Broadcasts.setBroadcastIntentFilter();

        // set REST API
        Util.setRestAPI();

        // initialize lists
        Lists.initializeLists();

        // set download intent service
        Util.setIntentServices();

        // set views
        setViews();

        // fetch movie data
        FetchData.startMovieDownloadService(FetchData.TYPE_DOWNLOAD_SERVICE_MULTIPLE_PAGE, 10);

        // fetch genre data
        FetchData.startMovieGenresDownloadService();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            if (hasFocus) {

                setAppFullScreen();

            }
        }

    }

    //Set app fullscreen
    public static void setAppFullScreen() {

        //Full screen
        mainActivity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }

    public static void setAppFullScreenStatusBar() {

        //Full screen with status bar showing
        mainActivity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }

    private void setViews() {

        Views.setViews();

    }


    @Override
    public void onBackPressed() {

        if (Views.screenLayout == 1) {

            // home
            super.onBackPressed();

        } else if (Views.screenLayout == 2) {

            // detailed movie
            if (YouTubeTrailer.isYouTubePlayerFullscreen) {

                // get out of full screen
                YouTubeTrailer.isYouTubePlayerFullscreen = false;
                YouTubeTrailer.videoPlayer.setFullscreen(YouTubeTrailer.isYouTubePlayerFullscreen);

            } else {

                // hide YouTube player
                YouTubeTrailer.hideYouTubePlayer();

                // go back to home screen
                Views.showHomeLayout();

            }

        } else if (Views.screenLayout == 3) {

            Views.checkMovieSortType();
            Views.showHomeLayout();

        } else {

            super.onBackPressed();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(Broadcasts.broadcastReceiver, Broadcasts.intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(Broadcasts.broadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(Util.intentServiceDownloadMovies);
        stopService(Util.intentServiceDownloadMovieTrailer);
        stopService(Util.intentServiceDownloadGenres);
    }
}
