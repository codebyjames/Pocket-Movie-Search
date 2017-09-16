package com.loboda.james.pocketmoviesearch.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.loboda.james.pocketmoviesearch.api.FetchData;
import com.loboda.james.pocketmoviesearch.service.GenreDownloadService;
import com.loboda.james.pocketmoviesearch.service.MovieDownloadService;
import com.loboda.james.pocketmoviesearch.service.TrailerDownloadService;
import com.loboda.james.pocketmoviesearch.util.Util;

/**
 * Created by Twaltex on 7/16/2017.
 */
public abstract class Broadcasts {

    public static final String BroadcastDownloadAllMoviesAction = "com.example.twaltex.twaltexmovieinspector.broadcasts.download.all.movies";
    public static final String BroadcastDownloadTrailerAction = "com.example.twaltex.twaltexmovieinspector.broadcasts.download.trailer";
    public static final String BroadcastDownloadGenresAction = "com.example.twaltex.twaltexmovieinspector.broadcasts.download.genres";

//    public static final String BroadcastFilterGenreAction = "com.example.twaltex.twaltexmovieinspector.broadcasts.filter.genre";
//    public static final String BroadcastSortMoviesAction = "com.example.twaltex.twaltexmovieinspector.broadcasts.sort.movies";

    public static IntentFilter intentFilter;
    public static BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            setBroadcastReceiver(intent);

        }
    };

    public static void setBroadcastIntentFilter(){

        intentFilter = new IntentFilter();

        intentFilter.addAction(BroadcastDownloadAllMoviesAction);
        intentFilter.addAction(BroadcastDownloadTrailerAction);
        intentFilter.addAction(BroadcastDownloadGenresAction);

    }

    public static void setBroadcastReceiver(Intent intent){


        if (intent.getAction().equals(BroadcastDownloadAllMoviesAction)) {

            // MOVIES DOWNLOAD
            processMovieDownload(intent);

        } else if (intent.getAction().equals(BroadcastDownloadTrailerAction)) {

            // TRAILER DOWNLOAD
            processTrailerDownload(intent);

        } else if (intent.getAction().equals(BroadcastDownloadGenresAction)) {

            // GENRES DOWNLOAD
            processGenresDownload(intent);

        }

    }

    private static void processMovieDownload(Intent intent){

        int downloadFinishedValue = intent.getIntExtra("finished", 0);

        if (downloadFinishedValue == MovieDownloadService.DOWNLOAD_FINISHED_NO_SORT) {

            // finished
            FetchData.isFinishedDownloadingMovies = true;

        } else if (downloadFinishedValue == MovieDownloadService.DOWNLOAD_FINISHED_WITH_SORT_NEEDED) {

            // finished
            FetchData.isFinishedDownloadingMovies = true;

        } else {

            // error or not able to finish
            FetchData.isFinishedDownloadingMovies = false;

        }

        // update UI
        Util.updateUIMovieBroadcast(downloadFinishedValue);

    }

    private static void processTrailerDownload(Intent intent){

        int downloadFinishedValue = intent.getIntExtra("finished", 0);

        if (downloadFinishedValue == TrailerDownloadService.DOWNLOAD_FINISHED_NO_SORT) {

            // finished
            FetchData.isFinishedDownloadingMovieTrailer = true;

        } else {

            // error or not able to finish
            FetchData.isFinishedDownloadingMovieTrailer = false;

        }

        // update UI
        Util.updateUITrailerBroadcast();

    }

    private static void processGenresDownload(Intent intent){

        int downloadFinishedValue = intent.getIntExtra("finished", 0);

        if (downloadFinishedValue == GenreDownloadService.DOWNLOAD_FINISHED_NO_SORT) {

            // finished
            FetchData.isFinishedDownloadingGenres = true;

        } else {

            // error or not able to finish
            FetchData.isFinishedDownloadingGenres = false;

        }

        // update UI
        Util.updateUIGenreBroadcast();

    }

}
