package com.loboda.james.pocketmoviesearch.adapter.genres;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loboda.james.pocketmoviesearch.R;
import com.loboda.james.pocketmoviesearch.colors.ColorChoices;
import com.loboda.james.pocketmoviesearch.fonts.Fonts;
import com.loboda.james.pocketmoviesearch.model.Genre;
import com.loboda.james.pocketmoviesearch.util.Util;

import java.util.List;


/**
 * Created by Twaltex on 7/7/2017.
 */

public class GenresAdapter extends RecyclerView.Adapter<GenreViewHolder> {

    private List<Genre> genres;
    private int rowLayout;
    private Context context;

    public GenresAdapter(List<Genre> genres, int rowLayout, Context context) {

        this.genres = genres;
        this.rowLayout = rowLayout;
        this.context = context;

    }

    @Override
    public GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GenreViewHolder holder, final int position) {

        // process UI & fetch data if needed
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                try {

                    // manipulate data
                    processBindViewHolderData(holder, position);

                } catch (Exception ex) {

                    // encountered problem with notify data change or scrolling recyclerview
                    ex.printStackTrace();

                }

            }
        };

        handler.post(runnable);


    }

    private void processBindViewHolderData(final GenreViewHolder holder, final int position){


        // check for selected genre
        checkForSelectedGenre(holder, position);

        final String textName = genres.get(position).getName().toLowerCase();
        holder.name.setText(textName);

        // On Click
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Set current genre
                Util.currentGenreSelectedIndex = position;

                GenresAdapter.this.notifyDataSetChanged();

                // process genre click
                Util.processButtonGenreClick();

            }
        });


    }

    /**
     * Check to see if item in adapter has been selected, check by position
     * @param holder
     * @param position
     */
    private void checkForSelectedGenre(GenreViewHolder holder, int position){

        // clear previous text colors
        showUnselectedItem(holder);

        // check if any genre selected or not
        if (Util.currentGenreSelectedIndex == 0 && position == 0) {

            // genre all selected = default
            showSelectedItem(holder);

        } else if (Util.currentGenreSelectedIndex == position) {

            // selected index = current position in adapter (so change)
            showSelectedItem(holder);

        } else {

            showUnselectedItem(holder);

        }

    }

    private void showUnselectedItem(GenreViewHolder holder){

        holder.name.setBackgroundResource(R.drawable.transparent_item);
        holder.name.setTextColor(ColorChoices.color_dark_gray);
        holder.name.setTypeface(Fonts.font_roboto_light, Typeface.NORMAL);

    }

    private void showSelectedItem(GenreViewHolder holder){

//        holder.name.setBackgroundResource(R.drawable.button_gray);
        holder.name.setTypeface(Fonts.font_roboto_light, Typeface.BOLD_ITALIC);

    }

    @Override
    public int getItemCount() {
        return genres.size();
    }


}
