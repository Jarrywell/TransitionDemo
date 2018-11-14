package com.android.test.transition.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.ArcMotion;
import android.support.transition.ChangeBounds;
import android.support.transition.ChangeImageTransform;
import android.support.transition.ChangeTransform;
import android.support.transition.Slide;
import android.support.transition.Transition;
import android.support.transition.TransitionInflater;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * des:TransitionManager.beginDelayedTransition()原理则是通过代码改变view的属性创造start scene和end scene
 *
 * author: libingyan
 * Date: 18-11-12 15:32
 */
public class SimpleTransitionActivity extends AppCompatActivity {

    private final String TAG = "SimpleTransitionActivity";
    private ViewGroup mParentView;
    private View mTargetView;
    private int mIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_transition);

        mParentView = findViewById(R.id.activity_simple_transition);
        mTargetView = findViewById(R.id.btn_target_view);

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * AutoTranstion
                 * 默认动画效果
                 */
                /*TransitionManager.beginDelayedTransition(mParentView, new AutoTransition());
                mTargetView.setPadding(100, 100, 100, 100);*/

                /**
                 * ChangeBounds
                 * ChangeBounds类是分析比较两个scene中view的位置边界创建移动和缩放动画
                 */
                /*ChangeBounds changeBounds = new ChangeBounds();
                changeBounds.setDuration(2000);
                TransitionManager.beginDelayedTransition(mParentView, changeBounds);
                mTargetView.setPadding(100, 100, 100, 100);*/

                /**
                 * ChangeClipBounds
                 * 检测view的剪切区域的位置边界，和ChangeBounds类似。
                 * 不过ChangeBounds针对的是view而ChangeClipBounds针对的是view的剪
                 * 切区域(setClipBound(Rect rect) 中的rect)，如果没有设置则没有动画效果。
                 */
                /*Rect rect = new Rect(50, 150, 200, 350);
                ChangeClipBounds changeClipBounds = new ChangeClipBounds();
                ViewCompat.setClipBounds(mTargetView, rect);
                TransitionManager.beginDelayedTransition(mParentView, changeClipBounds);
                mTargetView.setPadding(100, 100, 100, 100);*/

                /**
                 * ChangeTransform
                 * 检测view的scale和rotation创建缩放和旋转动画
                 */
                /*ChangeTransform changeTransform = new ChangeTransform();
                changeTransform.setDuration(500);
                TransitionManager.beginDelayedTransition(mParentView, changeTransform);
                mTargetView.setRotation(mIndex * 90);*/

                /**
                 * ChangeImageTransform
                 * 检测ImageView（这里是专指ImageView）的尺寸，位置以及ScaleType，并创建相应动画。
                 */
                /*ChangeImageTransform changeImageTransform = new ChangeImageTransform();
                changeImageTransform.setDuration(500);
                TransitionManager.beginDelayedTransition(mParentView, changeImageTransform);
                mTargetView.setRotation(mIndex * 90);*/

                /**
                 * Explode
                 * 根据view的visibility的不同创建爆炸动画
                 */
                /*Explode explode = new Explode();
                explode.setDuration(500);
                TransitionManager.beginDelayedTransition(mParentView, explode);
                mTargetView.setVisibility(mIndex % 2 == 0 ? View.GONE : View.VISIBLE);*/

                /**
                 * Fade
                 * 根据view的visibility的不同创建渐入动画
                 */
                /*Fade fade = new Fade();
                fade.setDuration(500);
                TransitionManager.beginDelayedTransition(mParentView, fade);
                mTargetView.setVisibility(mIndex % 2 == 0 ? View.GONE : View.VISIBLE);*/

                /**
                 * Slide
                 * 根据view的visibility的不同创建滑动动画
                 */
                /*Slide slide = new Slide();
                //Slide slide = new Slide(Gravity.RIGHT);
                slide.setDuration(500);
                TransitionManager.beginDelayedTransition(mParentView, slide);
                mTargetView.setVisibility(mIndex % 2 == 0 ? View.GONE : View.VISIBLE);*/

                /**
                 * ArcMotion
                 */
                /*ChangeBounds changeBoundsPath = new ChangeBounds();
                changeBoundsPath.setDuration(500);
                changeBoundsPath.setPathMotion(new ArcMotion());
                TransitionManager.beginDelayedTransition(mParentView, changeBoundsPath);

                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mTargetView.getLayoutParams();
                layoutParams.gravity = mIndex % 2 == 0 ? Gravity.BOTTOM : Gravity.TOP;
                mTargetView.setLayoutParams(layoutParams);*/


                /**
                 * PathMotion
                 */
                /*final Path path = new Path();
                path.moveTo(0, 0);
                path.quadTo(300, 0, 600, 450);

                PathMotion pathMotion = new PathMotion() {
                    @Override
                    public Path getPath(float startX, float startY, float endX, float endY) {
                        return path;
                    }
                };
                ChangeBounds changeBoundsPath = new ChangeBounds();
                changeBoundsPath.setDuration(500);
                changeBoundsPath.setPathMotion(pathMotion);
                TransitionManager.beginDelayedTransition(mParentView, changeBoundsPath);

                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mTargetView.getLayoutParams();
                layoutParams.gravity = Gravity.BOTTOM;
                mTargetView.setLayoutParams(layoutParams);*/

                /**
                 * 自定义Transition
                 */
                //代码实现
                /*Transition testSlideTransition = new TestSlideTransition();
                testSlideTransition.setDuration(500);
                TransitionManager.beginDelayedTransition(mParentView, testSlideTransition);
                mTargetView.setVisibility(mIndex % 2 == 0 ? View.GONE : View.VISIBLE);*/

                /**
                 * XML中的自定义Transition 跑不起来!!不支持在xml中使用自定义transition
                 */
                /*Transition transition = TransitionInflater.from(SimpleTransitionActivity.this).inflateTransition(R.transition.test_transition);
                TransitionManager.beginDelayedTransition(mParentView, transition);
                mTargetView.setVisibility(mIndex % 2 == 0 ? View.GONE : View.VISIBLE);*/

                mIndex++;
            }
        });
    }
}
