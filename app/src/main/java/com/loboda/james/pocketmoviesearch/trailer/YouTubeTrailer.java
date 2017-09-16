package com.loboda.james.pocketmoviesearch.trailer;

import android.view.View;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.loboda.james.pocketmoviesearch.BuildConfig;
import com.loboda.james.pocketmoviesearch.detailed_movie.DetailedMovie;
import com.loboda.james.pocketmoviesearch.views.Views;

/**
 * Created by Twaltex on 7/13/2017.
 */

public abstract class YouTubeTrailer {

    private static YouTubePlayer.OnInitializedListener onInitializedListener;

    private static final String API_KEY = BuildConfig.YOUTUBE_API_TOKEN;

    public static boolean isYouTubePlayerShowing = false;
    public static boolean isYouTubePlayerFullscreen = false;
    public static boolean isVideoPlaying = false;
    public static YouTubePlayer videoPlayer;
    public static String keyYouTubeVideo = "";

    public static void setYouTubeTrailer(){

        initializeYouTubeInitializedListener();

        // initialize player
        Views.youTubePlayerView.initialize(API_KEY, onInitializedListener);

    }

    public static void showYouTubePlayer(){

        keyYouTubeVideo = DetailedMovie.getTrailerKeyYouTube();

        Views.holderPosterDetailedMovie.setVisibility(View.GONE);
        Views.youTubePlayerView.setVisibility(View.VISIBLE);
        isYouTubePlayerShowing = true;

        // load new video
        loadYouTubeVideo();

    }

    public static void hideYouTubePlayer(){

        if (isVideoPlaying) {
            videoPlayer.pause();
        }

        Views.youTubePlayerView.setVisibility(View.GONE);
        isYouTubePlayerShowing = false;
        Views.holderPosterDetailedMovie.setVisibility(View.VISIBLE);

    }

    private static void initializeYouTubeInitializedListener(){

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {


                videoPlayer = youTubePlayer;

                if (!wasRestored) {

                    videoPlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                        @Override
                        public void onFullscreen(boolean isFullscreen) {

                            isYouTubePlayerFullscreen = isFullscreen;

                        }
                    });

                    loadYouTubeVideo();

                } else {

                    loadYouTubeVideo();

                }

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                isYouTubePlayerFullscreen = false;
                isVideoPlaying = false;
                hideYouTubePlayer();

            }
        };

    }

    private static void loadYouTubeVideo() {

        try {

            keyYouTubeVideo = DetailedMovie.getTrailerKeyYouTube();
            videoPlayer.loadVideo(keyYouTubeVideo);
            isVideoPlaying = true;

        } catch (Exception ex) {

            isVideoPlaying = false;

            // problem getting video, so use default: never have i ever
            keyYouTubeVideo = "L0BTJM56XAw";
            ex.printStackTrace();
        }

    }

}
