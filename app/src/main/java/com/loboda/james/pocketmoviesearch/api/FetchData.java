package com.loboda.james.pocketmoviesearch.api;

import android.app.Activity;

import com.loboda.james.pocketmoviesearch.BuildConfig;
import com.loboda.james.pocketmoviesearch.activity.MainActivity;
import com.loboda.james.pocketmoviesearch.lists.Lists;
import com.loboda.james.pocketmoviesearch.model.Genre;
import com.loboda.james.pocketmoviesearch.model.GenreResponse;
import com.loboda.james.pocketmoviesearch.model.Movie;
import com.loboda.james.pocketmoviesearch.model.MoviesResponse;
import com.loboda.james.pocketmoviesearch.model.Trailer;
import com.loboda.james.pocketmoviesearch.model.TrailersResponse;
import com.loboda.james.pocketmoviesearch.service.GenreDownloadService;
import com.loboda.james.pocketmoviesearch.service.MovieDownloadService;
import com.loboda.james.pocketmoviesearch.service.TrailerDownloadService;
import com.loboda.james.pocketmoviesearch.sort.SortOrder;
import com.loboda.james.pocketmoviesearch.util.Util;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fetch JSON data using REST ApiClient & ApiService
 * Created by Twaltex on 7/8/2017.
 */

public class FetchData {

    public final static String API_KEY = BuildConfig.THE_MOVIE_DB_API_TOKEN; // gradle.properties
    public static ApiService apiService;
    public static Activity context = MainActivity.mainActivity;
    public static int totalPages;
    public static int totalPagesConsumed;
    public static boolean isFinishedDownloadingMovies = false;
    public static boolean isFinishedDownloadingMovieTrailer = false;
    public static boolean isFinishedDownloadingGenres = false;
    public static final int TYPE_DOWNLOAD_SERVICE_NEXT_PAGE = 1;
    public static final int TYPE_DOWNLOAD_SERVICE_MULTIPLE_PAGE = 2;
    public static Movie trailerMovie;
    public static int trailerMovieId;

    // Information to sent to Service
    public static int downloadType = 1; // 1, 2
    public static int downloadNumberPages = 1;


    public static void setRestAPI(){

        apiService = ApiClient.getClient().create(ApiService.class);

    }

    /**
     * Fetch REST Data from a particular page
     * @param page : page number for results JSON
     */
    public static void getSinglePageResults(int page){

        // GET MOVIES

        Call<MoviesResponse> call = apiService.getPopularMovies(API_KEY, page);

        switch (Util.movieSortType) {
            case 1:
                call = apiService.getPopularMovies(API_KEY, page);
                break;
            case 2:
                call = apiService.getTopRatedMovies(API_KEY, page);
                break;
            case 3:
                call = apiService.getNowPlayingMovies(API_KEY, page);
                break;
            case 4:
                call = apiService.getUpcomingMovies(API_KEY, page);
                break;
        }

        // Process Data
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                try {

                    // get total pages available & page that currently consuming
                    totalPages = response.body().getTotalPages();
                    totalPagesConsumed = response.body().getPage();

                    // get all movies from "results" JSON array
                    List<Movie> moviesResponse =  response.body().getResults();
                    Lists.movies.addAll(moviesResponse);

                    // clear response list
                    moviesResponse.clear();

                    // filter movies: remove any movies that do not belong to specific data sort
                    Util.filterMoviesByDate();

                    // remove movies without posters
                    Util.removeMoviesWithoutPoster();


                } catch (Exception ex) {

                    MovieDownloadService.downloadFinishedValue = MovieDownloadService.DOWNLOAD_FAILED_OR_ERROR;

                }

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

                MovieDownloadService.downloadFinishedValue = MovieDownloadService.DOWNLOAD_FAILED_OR_ERROR;

                Util.showToastMessage("Failed to Load Movies");

            }

        });

    }

    /**
     * Fetch REST Data from the next page
     * <>when user is scrolling continuously</>
     */
    public static void getNextPageResults(){

        if (totalPagesConsumed >= totalPages) {
            // do nothing : no more pages

        } else {

            // fetch data from next page
            getSinglePageResults(totalPagesConsumed + 1);

        }

    }


    /**
     * Fetch REST Data for a certain number of pages
     * @param pages
     */
    public static void getMultiplePageResults(int pages){

        for (int i = 1; i < (pages + 1) ; i++) {

            getSinglePageResults(i);

        }

    }


    /**
     * Fetch movie trailer using REST
     * <>then set movie trailer to trailer</>
     * @param movie
     * @param id
     */
    public static void getResultsMovieTrailer(final Movie movie, int id){


        Call<TrailersResponse> call = apiService.getMovieTrailers(id, API_KEY);

        // Process Data
        call.enqueue(new Callback<TrailersResponse>() {
            @Override
            public void onResponse(Call<TrailersResponse> call, Response<TrailersResponse> response) {

                try {

                    // get all trailers from "results" JSON array
                    List<Trailer> trailers = response.body().getResults();

                    if (trailers == null || trailers.isEmpty()) {

                        // no trailers
                        movie.setYoutubeTrailerKey("0");

                    } else {

                        // keep first trailer (YouTube key) only
                        String keyTrailer = trailers.get(0).getYoutubeKey();
                        movie.setYoutubeTrailerKey(keyTrailer);
                    }

                    // clear response list
                    trailers.clear();

                } catch (Exception ex) {

                    TrailerDownloadService.downloadFinishedValue = TrailerDownloadService.DOWNLOAD_FAILED_OR_ERROR;

                }

            }

            @Override
            public void onFailure(Call<TrailersResponse> call, Throwable t) {

                TrailerDownloadService.downloadFinishedValue = TrailerDownloadService.DOWNLOAD_FAILED_OR_ERROR;

            }

        });

    }

    public static void getResultsGenre(){

        Call<GenreResponse> call = apiService.getGenres(API_KEY);

        // Process Data
        call.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {

                try {

                    // get all genres from "results" JSON array
                    List<Genre> genres = response.body().getResults();
                    Lists.genres.addAll(genres);

                    // clear response list
                    genres.clear();

                    // sort genres
                    Collections.sort(Lists.genres, SortOrder.GENRES_ALPHABETICAL);

                    // add default genre object = 0, All to front of list
                    Genre defaultGenre = new Genre(0, "All");
                    Lists.genres.add(0, defaultGenre);

                } catch (Exception ex) {

                    GenreDownloadService.downloadFinishedValue = GenreDownloadService.DOWNLOAD_FAILED_OR_ERROR;

                }

            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {

                GenreDownloadService.downloadFinishedValue = GenreDownloadService.DOWNLOAD_FAILED_OR_ERROR;

            }
        });

    }


    /**
     * Process Download Service handled inside of DownloadService
     */
    public static void processMovieDownloadService(){

        if (downloadType == TYPE_DOWNLOAD_SERVICE_NEXT_PAGE) {

            getNextPageResults();

        } else if (downloadType == TYPE_DOWNLOAD_SERVICE_MULTIPLE_PAGE) {

            getMultiplePageResults(downloadNumberPages);

        }

    }

    public static void processTrailerDownloadService(){

        getResultsMovieTrailer(trailerMovie, trailerMovieId);

    }

    public static void processGenresDownloadService(){

        getResultsGenre();

    }

    /**
     * Start download movies
     * @param type : next page or multiple pages download
     * @param pages : number of pages
     */
    public static void startMovieDownloadService(int type, int pages){

        downloadType = type;
        downloadNumberPages = pages;

        context.startService(Util.intentServiceDownloadMovies);

    }


    /**
     * Start download trailer for movie
     * @param movie : movie
     * @param id : id of movie
     */
    public static void startMovieTrailerDownloadService(Movie movie, int id){

        trailerMovie = movie;
        trailerMovieId = id;

        context.startService(Util.intentServiceDownloadMovieTrailer);

    }

    /**
     * Start download movie genres
     */
    public static void startMovieGenresDownloadService(){

        context.startService(Util.intentServiceDownloadGenres);

    }

}
