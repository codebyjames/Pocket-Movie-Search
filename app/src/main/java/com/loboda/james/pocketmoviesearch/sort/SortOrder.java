package com.loboda.james.pocketmoviesearch.sort;

import com.loboda.james.pocketmoviesearch.model.Genre;
import com.loboda.james.pocketmoviesearch.model.Movie;
import com.loboda.james.pocketmoviesearch.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Twaltex on 7/8/2017.
 */

public class SortOrder {

    // region SORT BY RECENT DATE DESCENDING
    public static final Comparator<Movie> RECENT_DATE_ORDER_DESCENDING = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie movie2) {

            int dateDay = 0, dateDay2 = 0;
            int dateMonth = 0, dateMonth2 = 0;
            int dateYear = 0, dateYear2 = 0;

            Calendar calendarDate = Calendar.getInstance();
            Calendar calendarDate2 = Calendar.getInstance();

            String date = movie.getReleaseDate();
            String date2 = movie2.getReleaseDate();

            if (date.length() > 0) {

                // split each number after " - "
                List<String> dateList = new ArrayList<>(Arrays.asList(date.split("-")));

                // reformat
                dateDay = Integer.valueOf(dateList.get(2));
                dateMonth = Util.getMonthNumber(dateList.get(1));
                dateYear = Integer.valueOf(dateList.get(0));

                calendarDate = Calendar.getInstance();
                calendarDate.set(dateYear, dateMonth, dateDay, 0, 0);

            }

            if (date2.length() > 0) {

                // split each number after " - "
                List<String> dateList2 = new ArrayList<>(Arrays.asList(date2.split("-")));

                // reformat
                dateDay2 = Integer.valueOf(dateList2.get(2));
                dateMonth2 = Util.getMonthNumber(dateList2.get(1));
                dateYear2 = Integer.valueOf(dateList2.get(0));

                calendarDate2 = Calendar.getInstance();
                calendarDate2.set(dateYear2, dateMonth2, dateDay2, 0, 0);

            }


            return calendarDate2.compareTo(calendarDate);

        }
    };
    // endregion a

    // region SORT BY RECENT DATE ASCENDING
    public static final Comparator<Movie> RECENT_DATE_ORDER_ASCENDING = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie movie2) {

            int dateDay = 0, dateDay2 = 0;
            int dateMonth = 0, dateMonth2 = 0;
            int dateYear = 0, dateYear2 = 0;

            Calendar calendarDate = Calendar.getInstance();
            Calendar calendarDate2 = Calendar.getInstance();

            String date = movie.getReleaseDate();
            String date2 = movie2.getReleaseDate();

            if (date.length() > 0) {

                // split each number after " - "
                List<String> dateList = new ArrayList<>(Arrays.asList(date.split("-")));

                // reformat
                dateDay = Integer.valueOf(dateList.get(2));
                dateMonth = Util.getMonthNumber(dateList.get(1));
                dateYear = Integer.valueOf(dateList.get(0));

                calendarDate = Calendar.getInstance();
                calendarDate.set(dateYear, dateMonth, dateDay, 0, 0);

            }

            if (date2.length() > 0) {

                // split each number after " - "
                List<String> dateList2 = new ArrayList<>(Arrays.asList(date2.split("-")));

                // reformat
                dateDay2 = Integer.valueOf(dateList2.get(2));
                dateMonth2 = Util.getMonthNumber(dateList2.get(1));
                dateYear2 = Integer.valueOf(dateList2.get(0));

                calendarDate2 = Calendar.getInstance();
                calendarDate2.set(dateYear2, dateMonth2, dateDay2, 0, 0);

            }


            return calendarDate.compareTo(calendarDate2);

        }
    };
    // endregion

    // region SORT BY POPULARITY
    public static final Comparator<Movie> POPULARITY_ORDER = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie movie2) {
            return movie2.getPopularity().compareTo(movie.getPopularity());
        }
    };
    // endregion

    // region SORT BY TOP RATING
    public static final Comparator<Movie> TOP_RATING_ORDER = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie movie2) {
            return movie2.getVoteAverage().compareTo(movie.getVoteAverage());
        }
    };
    // endregion

    // region SORT GENRES ALPHABETICAL

    public static final Comparator<Genre> GENRES_ALPHABETICAL = new Comparator<Genre>() {
        @Override
        public int compare(Genre genre, Genre genre2) {

            String genreName1 = genre.getName();
            String genreName2 = genre2.getName();

            int result = String.CASE_INSENSITIVE_ORDER.compare(genreName1, genreName2);

            if (result == 0) {
                result = genreName1.compareTo(genreName2);
            }

            return result;
        }
    };

    // endregion

}
