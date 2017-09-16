package com.loboda.james.pocketmoviesearch.adapter.genres;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loboda.james.pocketmoviesearch.R;
import com.loboda.james.pocketmoviesearch.fonts.Fonts;

/**
 * Created by Twaltex on 7/8/2017.
 */

public class GenreViewHolder extends RecyclerView.ViewHolder {

    LinearLayout genreLayout;
    TextView name;

    public GenreViewHolder(View view) {

        super(view);

        genreLayout = view.findViewById(R.id.genre_layout);
        name = view.findViewById(R.id.name);

        // fonts
        name.setTypeface(Fonts.font_roboto_light);

    }

}
