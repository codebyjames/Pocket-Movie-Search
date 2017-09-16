package com.loboda.james.pocketmoviesearch.animation;

import android.graphics.drawable.TransitionDrawable;
import android.view.View;

/**
 * Created by james on 1/11/2016.
 */
public class TransitionObject{

    public TransitionObject() {

    }

    /**
     * Transition default 200ms
     *
     * @param view
     */
    public static void transitionAnimation(View view) {
        TransitionDrawable transitionDrawable = (TransitionDrawable) view.getBackground();
        transitionDrawable.startTransition(200);
        transitionDrawable.reverseTransition(200);
    }

    /**
     *
     * Transition with start & reverse times
     *
     * @param view
     * @param start
     * @param reverse
     */
    public static void transitionAnimation(View view, int start, int reverse) {
        TransitionDrawable transitionDrawable = (TransitionDrawable) view.getBackground();
        transitionDrawable.startTransition(start);
        transitionDrawable.reverseTransition(reverse);

    }


    /**
     * Stop current transition
     *
     * @param view
     */
    public static void stopTransition(View view){

        TransitionDrawable transitionDrawable = (TransitionDrawable) view.getBackground();
        transitionDrawable.resetTransition();

    }

}
