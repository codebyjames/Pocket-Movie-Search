package com.loboda.james.pocketmoviesearch.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.loboda.james.pocketmoviesearch.activity.MainActivity;
import com.loboda.james.pocketmoviesearch.animation.AnimationObject;
import com.loboda.james.pocketmoviesearch.animation.AnimationRotate;
import com.loboda.james.pocketmoviesearch.api.FetchData;
import com.loboda.james.pocketmoviesearch.lists.Lists;
import com.loboda.james.pocketmoviesearch.model.Movie;
import com.loboda.james.pocketmoviesearch.service.GenreDownloadService;
import com.loboda.james.pocketmoviesearch.service.MovieDownloadService;
import com.loboda.james.pocketmoviesearch.service.TrailerDownloadService;
import com.loboda.james.pocketmoviesearch.sort.SortOrder;
import com.loboda.james.pocketmoviesearch.views.Views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created by Twaltex on 7/8/2017.
 */

public class Util {

    // keep track of last position in adapter: when changing from linear to grid layout
    public static Activity context = MainActivity.mainActivity;
    public static int lastKnowAdapterItemPositionLinear = 0;
    public static int lastKnowAdapterItemPositionGrid = 0;
    public static int movieSortType = 1; // 1 = popular, 2 = top, 3 = now playing, 4 = upcoming
    public static int upcomingMovieSort = 1; // 1 = this month, 2 = next month
    public static int currentGenreSelectedIndex = 0; // 0 = index in lists
    public static int currentGenreSelectedId = 0; // 0 = all
    public static String currentGenreSelectedName = "all"; // name = all
    public static Intent intentServiceDownloadMovies;
    public static Intent intentServiceDownloadMovieTrailer;
    public static Intent intentServiceDownloadGenres;

    /**
     * Format date from (Year-Month-Day) to (Month Day, Year)
     */
    public static String formatReleaseDate(String date) {

        if (date.length() > 0) {

            // split each number after " - "
            List<String> dateList = new ArrayList<>(Arrays.asList(date.split("-")));

            // reformat
            String day = dateList.get(2);
            String month = formatMonth(dateList.get(1));
            String year = dateList.get(0);
            date = month + " " + day + ", " + year;

        } else {

            date = "";

        }
        return date;

    }

    public static String formatReleaseDate2(String date) {

        // from year-month-day to month day, year

        // reformat
        String day = date.substring(8, date.length());
        String parseMonth = date.substring(5, 7);
        String month = formatMonth(parseMonth);
        String year = date.substring(0, 4);
        date = month + " " + day + ", " + year;

        return date;

    }

    /**
     * Format month from (##) to (Name)
     *
     * @param number
     * @return
     */
    public static String formatMonth(String number) {

        String monthString;

        switch (number) {
            case "01":
                monthString = "January";
                break;
            case "1":
                monthString = "January";
                break;
            case "02":
                monthString = "February";
                break;
            case "2":
                monthString = "February";
                break;
            case "03":
                monthString = "March";
                break;
            case "3":
                monthString = "March";
                break;
            case "04":
                monthString = "April";
                break;
            case "4":
                monthString = "April";
                break;
            case "05":
                monthString = "May";
                break;
            case "5":
                monthString = "May";
                break;
            case "06":
                monthString = "June";
                break;
            case "6":
                monthString = "June";
                break;
            case "07":
                monthString = "July";
                break;
            case "7":
                monthString = "July";
                break;
            case "08":
                monthString = "August";
                break;
            case "8":
                monthString = "August";
                break;
            case "09":
                monthString = "September";
                break;
            case "9":
                monthString = "September";
                break;
            case "10":
                monthString = "October";
                break;
            case "11":
                monthString = "November";
                break;
            case "12":
                monthString = "December";
                break;
            default:
                monthString = "";
                break;
        }

        return monthString;

    }

    /**
     * Get month number from a string
     *
     * @param number
     * @return
     */
    public static int getMonthNumber(String number) {

        int monthNumber;

        switch (number) {
            case "01":
                monthNumber = 1;
                break;
            case "1":
                monthNumber = 1;
                break;
            case "02":
                monthNumber = 2;
                break;
            case "2":
                monthNumber = 2;
                break;
            case "03":
                monthNumber = 3;
                break;
            case "3":
                monthNumber = 3;
                break;
            case "04":
                monthNumber = 4;
                break;
            case "4":
                monthNumber = 4;
                break;
            case "05":
                monthNumber = 5;
                break;
            case "5":
                monthNumber = 5;
                break;
            case "06":
                monthNumber = 6;
                break;
            case "6":
                monthNumber = 6;
                break;
            case "07":
                monthNumber = 7;
                break;
            case "7":
                monthNumber = 7;
                break;
            case "08":
                monthNumber = 8;
                break;
            case "8":
                monthNumber = 8;
                break;
            case "09":
                monthNumber = 9;
                break;
            case "9":
                monthNumber = 9;
                break;
            case "10":
                monthNumber = 10;
                break;
            case "11":
                monthNumber = 11;
                break;
            case "12":
                monthNumber = 12;
                break;
            default:
                monthNumber = 1;
                break;
        }

        return monthNumber;

    }

    /**
     * Format a number to three places ( ##.# ) and add a suffix (ex. 12.4k)
     *
     * @param number
     * @return
     */
    public static String formatNumberWithSuffix(int number) {

        String result;

        //round number up
        roundUp(number);

        if (number < 1000) {
            result = String.valueOf(number);
        } else {
            int exp = (int) (Math.log(number) / Math.log(1000));
            result = String.format("%.0f%c", number / Math.pow(1000, exp), "kMGTPE".charAt(exp -
                    1));

//            One Decimal point(above is 0 - no decimal point)
//            result = String.format("%.0f%c", number / Math.pow(1000, exp), "kMGTPE".charAt(exp -
//                    1));
        }

        return result;
    }

    /**
     * round number up depending on size of number
     **/
    public static int roundUp(int src) {
        int len = String.valueOf(src).length() - 1;
        if (len == 0) len = 1;
        int d = (int) Math.pow((double) 10, (double) len);
        return (src + (d - 1)) / d * d;
    }

    /**
     * Show a toast message
     *
     * @param message
     */
    public static void showToastMessage(String message) {

        Views.toast_textview.setText(message);
        Views.toast.show();

    }

    /**
     * Adjust Positions
     *
     * @see com.loboda.james.pocketmoviesearch.adapter.movies.MoviesAdapter
     * Linear to Grid: use linear position
     * Grid to Linear: subtract 3(since only 4 items shown per full screen) Position 0,1,2,3 (0-base)
     */
    public static void adjustLastKnownPositions() {

        if (Views.listLayoutType == 1) {

            // set new grid position
            try {

                Util.lastKnowAdapterItemPositionGrid = Util.lastKnowAdapterItemPositionLinear;

            } catch (Exception ex) {

                Util.lastKnowAdapterItemPositionLinear = 0;
                ex.printStackTrace();
            }

        } else {

            // set new linear position number
            try {

                Util.lastKnowAdapterItemPositionLinear = Util.lastKnowAdapterItemPositionGrid - 3;

            } catch (Exception ex) {

                Util.lastKnowAdapterItemPositionLinear = 0;
                ex.printStackTrace();
            }

        }

    }

    public static void goToLastKnownPosition() {

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if (Views.listLayoutType == 1) {
                    Views.recyclerViewMovies.scrollToPosition(lastKnowAdapterItemPositionLinear);
                } else {
                    Views.recyclerViewMovies.scrollToPosition(Util.lastKnowAdapterItemPositionGrid);
                }

            }
        };

        handler.post(runnable);

    }

    /**
     * Sort Movie List based on sort type
     *
     * @see SortOrder
     * @param delay
     */
    public static void sortMovieList(int delay) {

        // Delayed sort, so list can be sorted after all JSON data has been parsed
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                try {

                    switch (movieSortType) {
                        case 1:
                            // popular
                            Collections.sort(Lists.movies, SortOrder.POPULARITY_ORDER);
                            break;
                        case 2:
                            // top
                            Collections.sort(Lists.movies, SortOrder.TOP_RATING_ORDER);
                            break;
                        case 3:
                            // now playing
                            Collections.sort(Lists.movies, SortOrder.RECENT_DATE_ORDER_DESCENDING);
                            break;
                        case 4:
                            // upcoming
                            Collections.sort(Lists.movies, SortOrder.RECENT_DATE_ORDER_ASCENDING);
                            break;
                    }

                } catch (Exception ex) {

                    // problem sorting
                    ex.printStackTrace();
                }

            }
        };

        handler.postDelayed(runnable, delay); // delayed on purpose @see Clicks


    }

    /**
     * Refresh the current movie list by clearing the list & getting new results
     * <>Filter Genre when Genre selected</>
     */
    public static void refreshMoviesList() {

        Lists.movies.clear();

        if (movieSortType == 4) {

            // upcoming

            if (upcomingMovieSort == 1) {

                // this month
                FetchData.startMovieDownloadService(FetchData.TYPE_DOWNLOAD_SERVICE_MULTIPLE_PAGE, 12);

            } else {

                // next month
                FetchData.startMovieDownloadService(FetchData.TYPE_DOWNLOAD_SERVICE_MULTIPLE_PAGE, 12);

            }


        } else {

            // top, popular, now playing

            if (currentGenreSelectedId == 0) {

                // all
                FetchData.startMovieDownloadService(FetchData.TYPE_DOWNLOAD_SERVICE_MULTIPLE_PAGE, 10);

            } else {

                FetchData.startMovieDownloadService(FetchData.TYPE_DOWNLOAD_SERVICE_MULTIPLE_PAGE, 15);

            }

        }

    }

    /**
     * Reset Movie RecyclerView Adapter
     * <>on click genre</>
     * <>sorting</>
     */
    public static void resetMovieAdapter() {

        Views.setRecyclerMoviesAdapterLayout();

    }

    /**
     * Change Movie RecyclerView Adapter Layout
     * <>on click change layout type</>
     */
    public static void changeMovieAdapterLayout() {

        if (Views.listLayoutType == 1) {

            // change from linear to grid
            Views.listLayoutType = 2;
            Views.setRecyclerMoviesAdapterLayout();

        } else {

            // change from grid to linear
            Views.listLayoutType = 1;
            Views.setRecyclerMoviesAdapterLayout();

        }

        goToLastKnownPosition();

    }

    /**
     * Remove all movies without a poster image
     */
    public static void removeMoviesWithoutPoster() {

        ArrayList<Movie> removeMovies = new ArrayList<>();

        for (Movie item : Lists.movies) {

            if (item.getPosterPath().equals("https://image.tmdb.org/t/p/w500null")
                    || item.getPosterPath().equals("https://image.tmdb.org/t/p/w500/null")) {

                removeMovies.add(item);

            }

        }

        Lists.movies.removeAll(removeMovies);

    }

    /**
     * Generic On Click for Buttons of Movie Sort Type
     *
     * @param button   : button that is being clicked, movie sort type
     * @param sortType : integer, representing how to be sorted & which data to fetch
     */
    public static void processButtonMovieSortTypeClick(final View button, final int sortType) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AnimationObject.stopAnimation(button);
                AnimationObject.grow(button, 200, 1.03f);

                // hide genres recycler views (upcoming screen)
                if (sortType == 4) {

                    Views.holderRecyclerViewGenres.setVisibility(View.GONE);

                } else {

                    Views.holderRecyclerViewGenres.setVisibility(View.VISIBLE);

                }

                // show this month / next month switch layout
                if (sortType == 4) {

                    // upcoming
                    Views.holderSwitchUpcoming.setVisibility(View.VISIBLE);

                } else {
                    Views.holderSwitchUpcoming.setVisibility(View.GONE);
                }

                if (movieSortType != sortType) {

                    // reset selected genre
                    resetSelectedGenre();

                    // reset data
                    Lists.movies.clear();

                    movieSortType = sortType;

                    if (movieSortType == 4) {

                        if (upcomingMovieSort == 1) {

                            // this month
                            FetchData.startMovieDownloadService(FetchData.TYPE_DOWNLOAD_SERVICE_MULTIPLE_PAGE, 12);

                        } else {

                            // next month
                            FetchData.startMovieDownloadService(FetchData.TYPE_DOWNLOAD_SERVICE_MULTIPLE_PAGE, 12);

                        }

                    } else {

                        // top, popular, now playing

                        if (currentGenreSelectedId == 0) {

                            // all
                            FetchData.startMovieDownloadService(FetchData.TYPE_DOWNLOAD_SERVICE_MULTIPLE_PAGE, 10);

                        } else {

                            FetchData.startMovieDownloadService(FetchData.TYPE_DOWNLOAD_SERVICE_MULTIPLE_PAGE, 15);

                        }

                    }

                } else {

                    movieSortType = sortType;

                    if (Lists.movies != null && !Lists.movies.isEmpty()) {

                        Views.recyclerViewMovies.scrollToPosition(0);

                    }

                }

            }
        });

    }

    /**
     * On Click Genre from GenresAdapter
     */
    public static void processButtonGenreClick() {


        currentGenreSelectedName = Lists.genres.get(currentGenreSelectedIndex).getName().toLowerCase();
        currentGenreSelectedId = Lists.genres.get(currentGenreSelectedIndex).getId();

        // Filter Movies by Genre: refresh handles it
        refreshMoviesList();

    }

    /**
     * Reset selected genre to 0 = all
     */
    public static void resetSelectedGenre() {

        // reset genre selected
        currentGenreSelectedIndex = 0;
        currentGenreSelectedId = 0;
        currentGenreSelectedName = "all";

        // data changed
        Views.genresAdapter.notifyDataSetChanged();

    }

    /**
     * Filter movies by genre: remove movies that do not belong to the specific genre
     */
    public static void filterMoviesByGenre() {

        Handler handlerFilter = new Handler();
        Runnable runnableFilter = new Runnable() {
            @Override
            public void run() {

                List<Movie> moviesToKeep = new ArrayList<>();

                for (Movie item : Lists.movies) {

                    List<Integer> listGenreIds = item.getGenreIds();

                    // check if any IDs in list match currently selected genre
                    for (Integer id : listGenreIds) {

                        if (id == Util.currentGenreSelectedId) {

                            moviesToKeep.add(item);
                            continue;

                        }

                    }

                }

                Lists.movies = moviesToKeep;

                // reset adapter
                resetMovieAdapter();
            }
        };

        handlerFilter.post(runnableFilter);

    }

    /**
     * Filter movies by date: remove movies that do not belong
     */
    public static void filterMoviesByDate() {

        switch (Util.movieSortType) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                filterPlayingNowMovies();
                break;
            case 4:
                if (upcomingMovieSort == 1) {
                    filterUpcomingMoviesThisMonth();
                } else {
                    filterUpcomingMoviesNextMonth();
                }
                break;
        }

    }

    /**
     * Filter movies list
     * <>show only movies that are coming out this month, not before today's date</>
     */
    public static void filterUpcomingMoviesThisMonth() {

        ArrayList<Movie> moviesToRemove = new ArrayList<>();

        for (Movie item : Lists.movies) {

            String date = item.getReleaseDate();

            int dateDay = 12, dateMonth = 7, dateYear = 2017;

            if (date.length() > 0) {

                // split each number after " - "
                List<String> dateList = new ArrayList<>(Arrays.asList(date.split("-")));

                // reformat
                dateDay = Integer.valueOf(dateList.get(2));
                dateMonth = Util.getMonthNumber(dateList.get(1));
                dateYear = Integer.valueOf(dateList.get(0));

                // filter out movies this month
                processFilterMoviesThisMonth(moviesToRemove, item, dateMonth, dateDay, dateYear);

            }

        }

        Lists.movies.removeAll(moviesToRemove);

    }

    /**
     * Filter movies list
     * <>show only movies that are coming out next month, not before today's date</>
     */
    public static void filterUpcomingMoviesNextMonth() {

        ArrayList<Movie> moviesToRemove = new ArrayList<>();

        for (Movie item : Lists.movies) {

            String date = item.getReleaseDate();

            int dateDay = 12, dateMonth = 7, dateYear = 2017;

            if (date.length() > 0) {

                // split each number after " - "
                List<String> dateList = new ArrayList<>(Arrays.asList(date.split("-")));

                // reformat
                dateDay = Integer.valueOf(dateList.get(2));
                dateMonth = Util.getMonthNumber(dateList.get(1));
                dateYear = Integer.valueOf(dateList.get(0));

                // filter out movies next month
                processFilterMoviesNextMonth(moviesToRemove, item, dateMonth, dateYear);

            }

        }

        Lists.movies.removeAll(moviesToRemove);

    }

    /**
     * Filter movies list
     * <>show only movies that are playing now, before today's date</>
     */
    public static void filterPlayingNowMovies() {

        ArrayList<Movie> moviesToRemove = new ArrayList<>();

        for (Movie item : Lists.movies) {

            String date = item.getReleaseDate();

            int dateDay = 12, dateMonth = 7, dateYear = 2017;

            if (date.length() > 0) {

                // split each number after " - "
                List<String> dateList = new ArrayList<>(Arrays.asList(date.split("-")));

                // reformat
                dateDay = Integer.valueOf(dateList.get(2));
                dateMonth = Util.getMonthNumber(dateList.get(1));
                dateYear = Integer.valueOf(dateList.get(0));

                // filter out movies this month
                processFilterMoviesPlayingNow(moviesToRemove, item, dateMonth, dateDay, dateYear);

            }

        }

        Lists.movies.removeAll(moviesToRemove);

    }

    /**
     * Get current Date
     *
     * @return list: month, day, year (integers)
     */
    private static ArrayList<Integer> getCurrentDateListForm() {

        ArrayList<Integer> listDate = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        int currentDay, currentMonth, currentYear;

        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH) + 1;
        currentDay = calendar.get(Calendar.DATE);

        listDate.add(currentMonth);
        listDate.add(currentDay);
        listDate.add(currentYear);

        return listDate;

    }

    /**
     * Filter movies list to show movies only for this month
     * <>month: current date</>
     */
    private static void processFilterMoviesThisMonth(ArrayList<Movie> moviesToRemove, Movie item, int dateMonth, int dateDay, int dateYear) {

        /**
         * if movie is after current date, then keep it inside of movie list, else remove
         */

        // month, day, year
        ArrayList<Integer> currentDate = getCurrentDateListForm();

        if (dateYear == currentDate.get(2)) {


            // movie is in current year

            if (dateMonth == currentDate.get(0)) {


                // movie is in current month

                if (dateDay > currentDate.get(1)) {

                    // movie is after today's date

                } else {

                    // movie is not after today's date
                    moviesToRemove.add(item);

                }

            } else {

                // movie is not in same month
                moviesToRemove.add(item);
            }

        } else {

            // movie is not in same year
            moviesToRemove.add(item);
        }

    }

    /**
     * Filter movies list to show movies only for next month
     * <>month after today's date</>
     */
    private static void processFilterMoviesNextMonth(ArrayList<Movie> moviesToRemove, Movie item, int dateMonth, int dateYear) {

        /**
         * if movie is month after current date, then keep it inside of movie list, else remove
         */

        // month, day, year
        ArrayList<Integer> currentDate = getCurrentDateListForm();

        int nextYear = (currentDate.get(2) + 1);

        if (dateYear == currentDate.get(2) || (dateMonth == 12 && dateYear == nextYear)) {

            // movie is in current year (or movie is in current year, but if month = 12, then it will be year after

            if (dateMonth == checkIfNextMonth12(currentDate.get(0))) {

                // movie is in next month

            } else {

                // movie is not in same month
                moviesToRemove.add(item);
            }

        } else {

            // movie is not in same year
            moviesToRemove.add(item);
        }

    }

    /**
     * Filter movies list to show movies only playing now
     * <>month: current date</>
     */
    private static void processFilterMoviesPlayingNow(ArrayList<Movie> moviesToRemove, Movie item, int dateMonth, int dateDay, int dateYear) {

        /**
         * if movie is after current date, then keep it inside of movie list, else remove
         */


        // month, day, year
        ArrayList<Integer> currentDate = getCurrentDateListForm();

        int nextYear = (currentDate.get(2) + 1);

        if (dateYear == currentDate.get(2) || (dateMonth == 12 && dateYear == nextYear)) {

            // movie is in current year (or movie is in current year, but if month = 12, then it will be year after

            if (dateMonth <= checkIfNextMonth12(currentDate.get(0))) {

                // movie is in next month or past

                if (dateDay <= currentDate.get(1)) {

                    // movie is before today's date or at

                } else {

                    // movie is not after today's date
                    moviesToRemove.add(item);

                }

            } else {

                // movie is not in same month
                moviesToRemove.add(item);
            }

        } else {

            // movie is not in same year
            moviesToRemove.add(item);
            ;
        }

    }

    /**
     * If next month is 12, then following month is 1
     *
     * @param month
     * @return
     */
    private static int checkIfNextMonth12(int month) {

        int nextMonth;

        // check if next month is 12
        if (month == 12) {
            nextMonth = 1;
        } else {
            nextMonth = month + 1;
        }

        return nextMonth;

    }

    /**
     * Remove all whitespaces and non-visible characters
     *
     * @param input
     * @return
     */
    public static String removeAllWhitespaces(String input) {

        String newText = input.replaceAll("\\s", "");

        return newText;

    }

    /**
     * Remove front whitespaces and non-visible characters
     *
     * @param input
     * @return
     */
    public static String trimFrontWhitespaces(String input) {
        if (input == null) return input;
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isWhitespace(input.charAt(i)))
                return input.substring(i);
        }
        return "";
    }

    /**
     * Update UI
     * <>refresh</>
     */
    public static void updateUIWithSorting() {

        // sort movies
        Util.sortMovieList(300);
        Views.checkMovieSortType();

        updateUIGeneric(360);

    }

    public static void updateUINoSorting() {

        updateUIGeneric(0);

    }

    public static void updateUIGeneric(int delay) {

        // rotate refresh button
        AnimationRotate.rotateAnimation(Views.buttonRefreshList);

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if (FetchData.isFinishedDownloadingMovies) {

                    if (currentGenreSelectedId == 0) {

                        // add to adapter
                        if (FetchData.downloadType == FetchData.TYPE_DOWNLOAD_SERVICE_NEXT_PAGE) {

                            Views.moviesAdapter.notifyDataSetChanged();

                        } else {

                            Util.resetMovieAdapter();

                        }

                    } else {

                        filterMoviesByGenre();

                    }

                } else {

                    Util.resetMovieAdapter();

                }

                // stop rotation of refresh button
                AnimationRotate.stopAnimation(Views.buttonRefreshList);


            }
        };

        handler.postDelayed(runnable, delay);

    }

    public static void updateUIMovieBroadcast(int downloadFinishedValue) {

        if (downloadFinishedValue == MovieDownloadService.DOWNLOAD_FINISHED_NO_SORT) {

            updateUINoSorting();

        } else if (downloadFinishedValue == MovieDownloadService.DOWNLOAD_FINISHED_WITH_SORT_NEEDED) {

            updateUIWithSorting();

        } else {

            updateUINoSorting();

        }

    }

    public static void updateUITrailerBroadcast() {

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if (FetchData.isFinishedDownloadingMovieTrailer) {

                    // do nothing, no UI needed

                }

            }
        };

        handler.post(runnable);

    }

    public static void updateUIGenreBroadcast() {

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if (FetchData.isFinishedDownloadingGenres) {

                    Views.genresAdapter.notifyDataSetChanged();
                    Views.checkRecyclerGenresViewEmpty();

                } else {

                    // default genres
                    Lists.setDefaultGenres();
                    Views.genresAdapter.notifyDataSetChanged();
                    Views.checkRecyclerGenresViewEmpty();

                }

            }
        };

        handler.postDelayed(runnable, 100);

    }

    public static void setIntentServices() {

        intentServiceDownloadMovies = new Intent(context, MovieDownloadService.class);
        intentServiceDownloadMovieTrailer = new Intent(context, TrailerDownloadService.class);
        intentServiceDownloadGenres = new Intent(context, GenreDownloadService.class);

    }

    public static void setRestAPI() {

        FetchData.setRestAPI();

    }

}
