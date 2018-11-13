package com.android.test.transition.demo.share_element_transition;

import com.android.test.transition.demo.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Transition;

/**
 * des:
 * author: libingyan
 * Date: 18-11-13 14:12
 */
public class ShareElementTransitionB extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_element_transition_b);

        Transition transition = new ChangeBounds();
        transition.setDuration(1000);
        getWindow().setSharedElementEnterTransition(transition);
    }
}
