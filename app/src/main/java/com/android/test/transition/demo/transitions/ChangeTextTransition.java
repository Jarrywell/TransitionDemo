package com.android.test.transition.demo.transitions;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.Property;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Map;

/**
 * des: 对TextView的textSize和textColor的变换
 * author: libingyan
 * Date: 18-11-14 20:41
 */
public class ChangeTextTransition extends Transition {

    private static final String PROPNAME_TEXT_SIZE = "changeTextTransition:textSize";
    private static final String PROPNAME_TEXT_COLOR = "changeTextTransition:textColor";


    public ChangeTextTransition() {
        /**
         * 设置该transition适用的View类型
         */
        addTarget(TextView.class);
    }


    /**
     * 保存开始scene的关键值
     * @param transitionValues
     */
    @Override
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    /**
     * 保存结束scene的关键值
     * @param transitionValues
     */
    @Override
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }


    private void captureValues(TransitionValues transitionValues) {
        if (transitionValues.view instanceof TextView) {
            final TextView view = (TextView) transitionValues.view;
            transitionValues.values.put(PROPNAME_TEXT_SIZE, view.getTextSize());
            transitionValues.values.put(PROPNAME_TEXT_COLOR, view.getCurrentTextColor());
        }
    }

    /**
     * 根据开始和结束状态创建动画
     * @param sceneRoot
     * @param startValues
     * @param endValues
     * @return
     */
    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        if (startValues == null || endValues == null) {
            return null;
        }

        final TextView view = (TextView) endValues.view;

        Map<String, Object> startVals = startValues.values;
        Map<String, Object> endVals = endValues.values;

        final float startTextSize = (float) startVals.get(PROPNAME_TEXT_SIZE);
        final float endTextSize = (float) endVals.get(PROPNAME_TEXT_SIZE);
        ObjectAnimator textSizeAnimator = ObjectAnimator.ofFloat(view, new TextSizeProperty(), startTextSize, endTextSize);

        final int startColor = (int) startVals.get(PROPNAME_TEXT_COLOR);
        final int endColor = (int) endVals.get(PROPNAME_TEXT_COLOR);

        ObjectAnimator textColorAnimator = ObjectAnimator.ofArgb(view, new TextColorProperty(), startColor, endColor);

        AnimatorSet animators = new AnimatorSet();
        animators.playTogether(textSizeAnimator, textColorAnimator);

        return animators;
    }

    @Override
    public String[] getTransitionProperties() {
        return new String[] {PROPNAME_TEXT_SIZE, PROPNAME_TEXT_COLOR};
    }

    private class TextSizeProperty extends Property<TextView, Float> {

        public TextSizeProperty() {
            /**
             * 属性的类型, 属性的名称
             */
            super(Float.class, "textSize");
        }

        @Override
        public void set(TextView object, Float value) {
            object.setTextSize(TypedValue.COMPLEX_UNIT_PX, value);
        }

        @Override
        public Float get(TextView object) {
            return object.getTextSize();
        }
    }

    private class TextColorProperty extends Property<TextView, Integer> {

        public TextColorProperty() {
            super(Integer.class, "textColor");
        }

        @Override
        public void set(TextView object, Integer value) {
            object.setTextColor(value);
        }

        @Override
        public Integer get(TextView object) {
            return object.getCurrentTextColor();
        }
    }
}
