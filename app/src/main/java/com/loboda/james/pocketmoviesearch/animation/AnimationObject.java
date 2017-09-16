package com.loboda.james.pocketmoviesearch.animation;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;

import com.loboda.james.pocketmoviesearch.R;

/**
 * Created by codehouse-work on 2/2/2017.
 *
 * Animate any view with a file from anim directory
 * or use the methods provided.
 */

public class AnimationObject {


    public AnimationObject(){}

    /**
     * Animate View with anim source file
     *
     * @param view : any layout view
     * @param source : any animation file
     * @param custom_activity : Activity
     **/

    public static void animate(View view, int source, Activity custom_activity){

        Animation animation = AnimationUtils.loadAnimation(custom_activity, source);
        view.startAnimation(animation);

    }

    /**
     * Animate View with anim source file with a delay
     *
     * @param view : any layout view
     * @param source : any animation file
     * @param delay : run animation from a delayed handler
     *
     **/
    public static void animate(final View view, final int source, final int delay,
                               final Activity custom_activity){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Animation animation = AnimationUtils.loadAnimation(custom_activity, source);
                view.startAnimation(animation);

            }
        }, delay);

    }

    /** Generic AnimationSet */

    public static void useGenericAnimationSetStatic(Animation animation, View view, int count){

        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(animation);
        animationSet.setRepeatCount(count);
        view.setAnimation(animationSet);

    }

    /**
     * Fade Generic
     *
     * @param view
     * @param duration
     */
    public static void fade(View view, int duration) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(duration);
        view.startAnimation(anim);
    }

    /**
     * Fade Out Animation
     *
     * @param duration : how long animation will last
     * @param view : use view for animation
     *
     **/

    public static void fadeOutAnimation(View view, int duration){

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(duration);

        useGenericAnimationSetStatic(fadeOut, view, 0);

    }

    /**
     * Fade Out Animation
     *
     * @param duration : how long animation will last
     * @param view : use view for animation
     * @param delay : start animation after the delay
     *
     **/
    public static void fadeOutAnimation(final View view, final int duration, final int delay){

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(duration);
        fadeOut.setStartOffset(delay);

        useGenericAnimationSetStatic(fadeOut, view, 0);

    }

    /**
     * Fade In Animation
     *
     * @param duration : how long animation will last
     * @param view : use view for animation
     *
     **/

    public static void fadeInAnimation(View view, int duration){

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(duration);

        useGenericAnimationSetStatic(fadeIn, view, 0);

    }

    /**
     * Fade In Animation
     *
     * @param duration : how long animation will last
     * @param view : use view for animation
     * @param delay : start animation after the delay
     *
     **/

    public static void fadeInAnimation(View view, int duration, final int delay){

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(duration);
        fadeIn.setStartOffset(delay);

        useGenericAnimationSetStatic(fadeIn, view, 0);

    }

    /**
     * Grow in size
     *
     * @param view
     * @param duration
     */
    public static void grow(View view, int duration) {
        final ScaleAnimation growAnim = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);

        growAnim.setDuration(duration);
        view.startAnimation(growAnim);
    }

    /**
     * Grow in size: custom scale
     * @param view
     * @param duration
     * @param scale
     */
    public static void grow(View view, int duration, float scale) {
        final ScaleAnimation growAnim = new ScaleAnimation(scale, scale, scale, scale,
                Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);

        growAnim.setDuration(duration);
        view.startAnimation(growAnim);
    }

    /**
     * Shrink in size
     *
     * @param view
     * @param duration
     */
    public static void shrink(View view, int duration) {
        final ScaleAnimation shrinkAnim = new ScaleAnimation(1.5f, 1.0f, 1.5f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);

        shrinkAnim.setDuration(duration);
        view.startAnimation(shrinkAnim);
    }

    /**
     * Swing Left
     *
     * @param activity
     * @param view
     */
    public static void swingUpLeft(Activity activity, View view) {
        Animation animation = AnimationUtils.loadAnimation(activity, R.anim.swing_up_left);
        view.startAnimation(animation);
    }

    /**
     * Stop current animation
     * @param view
     */
    public static void stopAnimation(View view){

        view.clearAnimation();

    }

}
