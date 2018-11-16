package com.android.test.transition.demo.viewoverlay;

import com.android.test.transition.demo.R;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.ViewOverlay;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Toast;

import static android.animation.ValueAnimator.INFINITE;
import static android.animation.ValueAnimator.REVERSE;

/**
 * des:
 * author: libingyan
 * Date: 18-11-16 16:36
 */
public class TestViewOverlayActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_viewoverlay);

        final Button button = findViewById(R.id.id_test_overlay);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ViewGroup viewGroup = findViewById(R.id.id_test_viewgroup_overlay);
                final ViewGroupOverlay viewGroupOverlay = viewGroup.getOverlay();

                final View view = findViewById(R.id.id_overlay_button);
                if (view != null) {
                    viewGroupOverlay.add(view);

                    ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", viewGroup.getHeight());
                    ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation", 0, 0);
                    rotate.setRepeatCount(INFINITE);
                    rotate.setRepeatMode(REVERSE);

                    anim.addListener(new Animator.AnimatorListener() {

                        @Override
                        public void onAnimationStart(Animator arg0) {
                        }

                        @Override
                        public void onAnimationRepeat(Animator arg0) {
                        }

                        @Override
                        public void onAnimationEnd(Animator arg0) {
                            //viewGroupOverlay.remove(view);
                        }

                        @Override
                        public void onAnimationCancel(Animator arg0) {
                            //viewGroupOverlay.remove(view);
                        }
                    });


                    AnimatorSet set = new AnimatorSet();
                    set.playTogether(anim, rotate);
                    set.setDuration(2000);
                    set.start();

                }
            }
        });


        button.post(new Runnable() {
            @Override
            public void run() {
                /**
                 * 获取Button的Overlay
                 */
                final ViewOverlay buttonOverlay = button.getOverlay();

                final Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);

                final int drawableWidth = drawable.getIntrinsicWidth();
                final int drawableHeight = drawable.getIntrinsicHeight();

                final int left = (button.getWidth() - drawableWidth) / 2;
                final int top = (button.getHeight() - drawableHeight) / 2;

                drawable.setBounds(left, top, left + drawableWidth, top + drawableHeight);

                /**
                 * 将一个drawable 加入到overlay中
                 */
                buttonOverlay.add(drawable);

            }
        });

    }
}
