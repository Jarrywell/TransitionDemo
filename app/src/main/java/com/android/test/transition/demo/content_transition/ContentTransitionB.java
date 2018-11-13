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

        Transition transition = TransitionInflater.from(this)
            .inflateTransition(R.transition.content_transition_b_enter_bottom);

        getWindow().setEnterTransition(transition);
    }
}
