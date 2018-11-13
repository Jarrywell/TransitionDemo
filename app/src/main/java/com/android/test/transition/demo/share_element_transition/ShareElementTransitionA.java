package com.android.test.transition.demo.share_element_transition;

import com.android.test.transition.demo.R;
import com.android.test.transition.demo.content_transition.ContentTransitionB;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.View;

/**
 * des:
 * author: libingyan
 * Date: 18-11-13 14:12
 */
public class ShareElementTransitionA extends AppCompatActivity {

    private View mShareElementView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_element_transition_a);
        mShareElementView = findViewById(R.id.id_btn_start_B);

        /*Transition transition = new ChangeBounds();
        transition.setDuration(100);
        getWindow().setSharedElementExitTransition(transition);*/

        findViewById(R.id.id_btn_start_B).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityB();
            }
        });
    }

    private void startActivityB() {
        Intent intent = new Intent(this, ShareElementTransitionB.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
            new Pair<View, String>(mShareElementView, "shared_view_01"));
        startActivity(intent, options.toBundle());
    }
}
