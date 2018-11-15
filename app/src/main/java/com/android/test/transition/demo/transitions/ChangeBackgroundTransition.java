package com.android.test.transition.demo.transitions;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

/**
 * des: 背景颜色的transition
 * author: libingyan
 * Date: 18-11-15 19:20
 */
public class ChangeBackgroundTransition extends Transition {

    private static final String PROPNAME_BACKGROUND = "changeBackgroundTransition:background";


    public ChangeBackgroundTransition() {
        addTarget(View.class);
    }


    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }


    private void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        final Drawable drawable = view.getBackground();
        if (drawable instanceof ColorDrawable) {
            final int color = ((ColorDrawable) drawable).getColor();
            transitionValues.values.put(PROPNAME_BACKGROUND, color);
        }
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        if (startValues == null || endValues == null) {
            return null;
        }
        final View view = endValues.view;
        final int startBackground = (int) startValues.values.get(PROPNAME_BACKGROUND);
        final int endBackground = (int) endValues.values.get(PROPNAME_BACKGROUND);
        if (startBackground == endBackground) {
            return null;
        }
        ValueAnimator animator = ValueAnimator.ofObject(new ArgbEvaluator(), startBackground, endBackground);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Object value = animation.getAnimatedValue();
                if (value != null) {
                    view.setBackgroundColor((int)value);
                }
            }
        });

        return animator;
    }

    @Override
    public String[] getTransitionProperties() {
        return new String[] { PROPNAME_BACKGROUND };
    }
}
