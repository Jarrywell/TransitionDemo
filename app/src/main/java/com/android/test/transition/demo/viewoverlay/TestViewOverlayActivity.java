package com.android.test.transition.demo.viewoverlay;

import com.android.test.transition.demo.R;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
import android.widget.Button;

/**
 * des: 具体使用ViewOverlay的场景在Visibility.onDisappear()中，主要是实现一个removeview()后的动画效果
 * author: libingyan
 * Date: 18-11-16 16:36
 */
public class TestViewOverlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_viewoverlay);

        final Button btnOverlayTest = findViewById(R.id.id_test_overlay);

        /**
         * 展示在一个ViewOverlay添加Drawable
         */
        btnOverlayTest.post(new Runnable() {
            @Override
            public void run() {
                /**
                 * 获取Button的Overlay
                 */
                final ViewOverlay buttonOverlay = btnOverlayTest.getOverlay();

                final Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);

                final int drawableWidth = drawable.getIntrinsicWidth();
                final int drawableHeight = drawable.getIntrinsicHeight();

                final int left = (btnOverlayTest.getWidth() - drawableWidth) / 2;
                final int top = (btnOverlayTest.getHeight() - drawableHeight) / 2;

                drawable.setBounds(left, top, left + drawableWidth, top + drawableHeight);

                /**
                 * 将一个drawable 加入到overlay中
                 */
                buttonOverlay.add(drawable);

            }
        });


        /**
         * 展示ViewGroupOverlay上增加view,并在布局空间中执行动画
         */
        btnOverlayTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                final ViewGroup parent = findViewById(R.id.id_layout_test);

                animateOverlay(parent, view);

            }
        });

        /**
         * 当点击这个按钮，它被移动到第一层layout的ViewGroupOverlay中，因此按钮可以在整个屏幕上移动。
         */
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ViewGroup parent = findViewById(R.id.activity_test_viewoverlay);

                animateOverlay(parent, v);
            }
        });


        /**
         * 我们结合了两种动画效果，首先是一个渐变动画，当动画播放完了之后，我们将这个按钮添加到绿色区域
         * 所在的layout的ViewOverlay中，然后播放一个移动动画。
         */
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                ObjectAnimator fadeOut = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f);
                fadeOut.setDuration(1000);

                final ViewGroup parent = findViewById(R.id.greenContainer);

                fadeOut.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        animateOverlay(parent, v);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animateOverlay(parent, v);
                    }
                });

                fadeOut.start();
            }
        });


        /**
         * 我们没有使用ViewOverlay方案，仅仅是使用了一般的view动画效果，你可以看到按钮的活动范围
         * 限制在了他所处的FrameLayout中。
         */
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ViewGroup container = findViewById(R.id.orangeContainer);
                ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationY", -container.getHeight());
                animator.setDuration(2000);
                animator.start();
            }
        });

    }

    /**
     * Overlay动画
     * @param parent
     * @param target
     */
    private void animateOverlay(final ViewGroup parent, final View target) {
        final ViewGroupOverlay viewGroupOverlay = parent.getOverlay();
        if (target != null) {
            viewGroupOverlay.add(target);

            ObjectAnimator translation = ObjectAnimator.ofFloat(target, "translationY", parent.getHeight());
            ObjectAnimator rotate = ObjectAnimator.ofFloat(target, "rotation", 0, 360);
            ObjectAnimator alpha = ObjectAnimator.ofFloat(target, "alpha", 1f);
            translation.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationCancel(Animator animation) {
                    viewGroupOverlay.remove(target);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    viewGroupOverlay.remove(target);
                }
            });

            AnimatorSet animator = new AnimatorSet();
            animator.playTogether(translation, rotate, alpha);
            animator.setDuration(2000);
            animator.start();
        }

    }
}
