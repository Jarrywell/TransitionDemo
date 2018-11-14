package com.android.test.transition.demo;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.transition.TransitionValues;
import android.support.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;

/**
 * des:
 * author: libingyan
 * Date: 18-11-14 10:47
 */
public class TestSlideTransition extends Visibility {

    private final int BOTTOM_SLIDE_GAP = 100;

    @Override
    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues,
        TransitionValues endValues) {
        if (endValues == null) {
            return null;
        }
        float endY = view.getTranslationY();
        float startY = view.getTranslationY() + BOTTOM_SLIDE_GAP;
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, startY, endY);
        return objectAnimator;
    }

    @Override
    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues,
        TransitionValues endValues) {
        if (startValues == null) {
            return null;
        }
        float startY = view.getTranslationY();
        float endY = view.getTranslationY() + BOTTOM_SLIDE_GAP;
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, startY, endY);
        return objectAnimator;
    }
}
