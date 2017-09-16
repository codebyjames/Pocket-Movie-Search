package com.loboda.james.pocketmoviesearch.service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import com.loboda.james.pocketmoviesearch.api.FetchData;
import com.loboda.james.pocketmoviesearch.broadcasts.Broadcasts;

/**
 * Created by Twaltex on 7/17/2017.
 */

public class MovieDownloadService extends Service {

    public static final int DOWNLOAD_FINISHED_WITH_SORT_NEEDED = 1;
    public static final int DOWNLOAD_FAILED_OR_ERROR = 0;
    public static final int DOWNLOAD_FINISHED_NO_SORT = 2;
    public static int downloadFinishedValue = 0; // send extra broadcast

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // run in new thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                downloadMovies();

            }
        });

        thread.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        stopSelf();

    }

    private void downloadMovies(){

        // download movies
        AsyncDownloadMovies asyncDownloadMovies = new AsyncDownloadMovies();
        asyncDownloadMovies.execute();

    }

    public class AsyncDownloadMovies extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                // download data from API Service
                FetchData.processMovieDownloadService();

                // success
                downloadFinishedValue = DOWNLOAD_FINISHED_WITH_SORT_NEEDED;

            } catch (Exception ex) {
                ex.printStackTrace();

                // fail
                downloadFinishedValue = DOWNLOAD_FAILED_OR_ERROR;

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            try {

                if (FetchData.downloadType == FetchData.TYPE_DOWNLOAD_SERVICE_NEXT_PAGE) {

                    // don't sort
                    downloadFinishedValue = DOWNLOAD_FINISHED_NO_SORT;

                } else {

                    // sort needed
                    downloadFinishedValue = DOWNLOAD_FINISHED_WITH_SORT_NEEDED;

                }

            } catch (Exception ex) {
                ex.printStackTrace();

                // fail
                downloadFinishedValue = DOWNLOAD_FAILED_OR_ERROR;
            }

            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(Broadcasts.BroadcastDownloadAllMoviesAction);
            broadcastIntent.putExtra("finished", downloadFinishedValue); // 0 = failed, 1 = finished
            sendBroadcast(broadcastIntent);

            super.onPostExecute(aVoid);
        }
    }

}
