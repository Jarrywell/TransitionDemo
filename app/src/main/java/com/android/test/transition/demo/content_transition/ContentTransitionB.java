package com.android.test.transition.demo.content_transition;

import com.android.test.transition.demo.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;

/**
 * des:
 * author: libingyan
 * Date: 18-11-13 14:12
 */
public class ContentTransitionB extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_transition_b);

        /**
         * 触发场景：ContentTransitionA -> ContentTransitionB (startActivity)
         * A.ExitTransition() -> B.EnterTransition()
         */
        Transition enterTransition = TransitionInflater.from(this)
            .inflateTransition(R.transition.content_transition_b_enter_bottom);
        getWindow().setEnterTransition(enterTransition);

        /**
         * 触发场景：ContentTransitionB -> ContentTransitionA (Back)
         * B.ReturnTransition()
         *
         * 注意这里ReturnTransition()实现了view分两个方向退出（参考transition文件）
         * 另外在布局文件中需要将分割的viewGroup指定android:transitionGroup="true"，否则不生效
         */
        Transition exitTransition = TransitionInflater.from(this)
            .inflateTransition(R.transition.content_transition_b_exit_top_bottom);
        getWindow().setReturnTransition(exitTransition);
    }
}
