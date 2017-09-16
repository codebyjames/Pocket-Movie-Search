package com.loboda.james.pocketmoviesearch.adapter.movies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loboda.james.pocketmoviesearch.R;
import com.loboda.james.pocketmoviesearch.fonts.Fonts;

/**
 * Created by Twaltex on 7/8/2017.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {

    LinearLayout movieLayout;
    LinearLayout layoutTop;
    TextView releaseDate;
    TextView rating;
    TextView voteCount;
    ImageView posterImage;

    public MovieViewHolder(View view) {

        super(view);

        movieLayout = view.findViewById(R.id.movie_layout);
        layoutTop = view.findViewById(R.id.layout_top);
        releaseDate = view.findViewById(R.id.release_date);
        rating = view.findViewById(R.id.rating);
        voteCount = view.findViewById(R.id.vote_count);
        posterImage = view.findViewById(R.id.poster_image);

        // fonts
        releaseDate.setTypeface(Fonts.font_roboto_light);
        voteCount.setTypeface(Fonts.font_roboto_light);
        rating.setTypeface(Fonts.font_roboto_light);

    }

}
