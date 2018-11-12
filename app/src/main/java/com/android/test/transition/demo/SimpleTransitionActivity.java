package com.android.test.transition.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.ArcMotion;
import android.support.transition.ChangeBounds;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * des:
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
                 */
                /*TransitionManager.beginDelayedTransition(mParentView, new AutoTransition());
                mTargetView.setPadding(100, 100, 100, 100);*/

                /**
                 * ChangeBounds
                 */
                /*ChangeBounds changeBounds = new ChangeBounds();
                changeBounds.setDuration(2000);
                TransitionManager.beginDelayedTransition(mParentView, changeBounds);
                mTargetView.setPadding(100, 100, 100, 100);*/

                /**
                 * ChangeClipBounds
                 */
                /*Rect rect = new Rect(50, 150, 200, 350);
                ChangeClipBounds changeClipBounds = new ChangeClipBounds();
                ViewCompat.setClipBounds(mTargetView, rect);
                TransitionManager.beginDelayedTransition(mParentView, changeClipBounds);
                mTargetView.setPadding(100, 100, 100, 100);*/

                /**
                 * ChangeTransform
                 */
                /*ChangeTransform changeTransform = new ChangeTransform();
                changeTransform.setDuration(500);
                TransitionManager.beginDelayedTransition(mParentView, changeTransform);
                mTargetView.setRotation(mIndex * 90);*/


                /**
                 * Explode
                 */
                /*Explode explode = new Explode();
                explode.setDuration(500);
                TransitionManager.beginDelayedTransition(mParentView, explode);
                mTargetView.setVisibility(mIndex % 2 == 0 ? View.GONE : View.VISIBLE);*/

                /**
                 * Fade
                 */
                /*Fade fade = new Fade();
                fade.setDuration(500);
                TransitionManager.beginDelayedTransition(mParentView, fade);
                mTargetView.setVisibility(mIndex % 2 == 0 ? View.GONE : View.VISIBLE);*/

                /**
                 * Slide
                 */
                /*Slide slide = new Slide();
                //Slide slide = new Slide(Gravity.RIGHT);
                slide.setDuration(500);
                TransitionManager.beginDelayedTransition(mParentView, slide);
                mTargetView.setVisibility(mIndex % 2 == 0 ? View.GONE : View.VISIBLE);*/

                /**
                 * ArcMotion
                 */
                ChangeBounds changeBoundsPath = new ChangeBounds();
                changeBoundsPath.setDuration(500);
                changeBoundsPath.setPathMotion(new ArcMotion());
                TransitionManager.beginDelayedTransition(mParentView, changeBoundsPath);

                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mTargetView.getLayoutParams();
                layoutParams.gravity = mIndex % 2 == 0 ? Gravity.BOTTOM : Gravity.TOP;
                mTargetView.setLayoutParams(layoutParams);


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

                mIndex++;
            }
        });
    }
}
