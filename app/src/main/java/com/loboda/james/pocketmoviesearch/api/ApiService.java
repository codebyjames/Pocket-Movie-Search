package com.loboda.james.pocketmoviesearch.api;


import com.loboda.james.pocketmoviesearch.model.Detail;
import com.loboda.james.pocketmoviesearch.model.GenreResponse;
import com.loboda.james.pocketmoviesearch.model.MoviesResponse;
import com.loboda.james.pocketmoviesearch.model.TrailersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Twaltex on 7/7/2017.
 */

public interface ApiService {

    // language = en-US;
    // {id} = path @Path >     // custom id (input)

    // https://api.themoviedb.org/3/movie/top_rated?api_key=c7fa2b2a25db2ad64967a9b4055e42f3&language=en-US&page=1
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey,
                                           @Query("page") int page);

    @GET("movie/latest")
    Call<MoviesResponse> getLatestMovies(@Query("api_key") String apiKey,
                                         @Query("page") int page);

    @GET("movie/now_playing")
    Call<MoviesResponse> getNowPlayingMovies(@Query("api_key") String apiKey,
                                             @Query("page") int page);

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey,
                                          @Query("page") int page);

    @GET("movie/upcoming")
    Call<MoviesResponse> getUpcomingMovies(@Query("api_key") String apiKey,
                                           @Query("page") int page);

    // https://api.themoviedb.org/3/movie/24?api_key=c7fa2b2a25db2ad64967a9b4055e42f3&language=en-US

    @GET("movie/{id}")
        // custom id (input)
    Call<Detail> getMovieDetails(@Path("id") int id,
                                 @Query("api_key") String apiKey);

    // https://api.themoviedb.org/3/movie/263115/reviews?api_key=c7fa2b2a25db2ad64967a9b4055e42f3&language=en-US&page=1
    @GET("movie/{id}/reviews")
    Call<MoviesResponse> getMovieReviews(@Path("id") int id,
                                         @Query("api_key") String apiKey);

    // https://api.themoviedb.org/3/search/movie?api_key=c7fa2b2a25db2ad64967a9b4055e42f3&language=en-US&query=Black%20Mirror&page=1&include_adult=yes&year=2014
    @GET("search/movie")
    Call<MoviesResponse> getSpecificMovie(@Query("api_key") String apiKey,
                                          @Query("query") String query_movie_title,
                                          @Query("include_adult") String include_adult,
                                          @Query("year") int movie_year);

    @GET("search/movie")
    Call<MoviesResponse> getSpecificMovie(@Query("api_key") String apiKey,
                                          @Query("query") String query_movie_title,
                                          @Query("include_adult") String include_adult); // without year

    // https://api.themoviedb.org/3/movie/263115/videos?api_key=c7fa2b2a25db2ad64967a9b4055e42f3&language=en-US
    @GET("movie/{movie_id}/videos")
    Call<TrailersResponse> getMovieTrailers(@Path("movie_id") int movie_id,
                                            @Query("api_key") String apiKey);

    // https://api.themoviedb.org/3/genre/movie/list?api_key=c7fa2b2a25db2ad64967a9b4055e42f3&language=en-US
    @GET("genre/movie/list")
    Call<GenreResponse> getGenres(@Query("api_key") String apiKey);

}
