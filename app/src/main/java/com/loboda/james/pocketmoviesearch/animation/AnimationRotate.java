package com.loboda.james.pocketmoviesearch.animation;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;

/**
 * Created by James on 5/31/2016.
 */
public class AnimationRotate {

    public AnimationRotate() {

    }

    public static void rotateAnimation(View view) {

        //Full Circle spin
        RotateAnimation rotate = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f); //where it pivots (0.2f far)

        rotate.setFillAfter(true);
        rotate.setDuration(500);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());

        view.startAnimation(rotate);

    }

    public static void rotateAnimation(View view, int duration) {

        //Full Circle spin
        RotateAnimation rotate = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotate.setFillAfter(true);
        rotate.setDuration(duration);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());

        view.startAnimation(rotate);

    }

    public static void rotateAnimation(View view, int from , int to) {

        RotateAnimation rotate = new RotateAnimation(from, to, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotate.setFillAfter(true);
        rotate.setDuration(500);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());

        view.startAnimation(rotate);

    }

    public static void rotateAnimation(View view, int from , int to, int duration) {

        RotateAnimation rotate = new RotateAnimation(from, to, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotate.setFillAfter(true);
        rotate.setDuration(duration);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());

        view.startAnimation(rotate);

    }

    public static void stopAnimation(View view){

        view.clearAnimation();

    }

}
