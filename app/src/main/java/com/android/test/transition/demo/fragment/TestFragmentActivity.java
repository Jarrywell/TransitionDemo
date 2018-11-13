package com.android.test.transition.demo.fragment;

import com.android.test.transition.demo.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.Slide;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * des:
 * author: libingyan
 * Date: 18-11-13 20:40
 */
public class TestFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment_layout);

        Fragment fragment1 = new TransitionFragment1();
        fragment1.setExitTransition(new Slide());

        getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.activity_fragment_container, fragment1)
            .commit();

    }
}
