package com.loboda.james.pocketmoviesearch.fonts;

import android.app.Activity;
import android.graphics.Typeface;

import com.loboda.james.pocketmoviesearch.activity.MainActivity;

/**
 * Created by James on 4/1/2017.
 */

public class Fonts {

    public static Typeface font_roboto_light;

    private static Activity contextActivity = MainActivity.mainActivity;

    public static void setFonts(){

        font_roboto_light = Typeface.createFromAsset(contextActivity.getBaseContext().getAssets(), "fonts/Roboto-Light.ttf");

    }

}
