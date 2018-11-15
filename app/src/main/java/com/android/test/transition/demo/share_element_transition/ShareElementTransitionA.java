package com.android.test.transition.demo.share_element_transition;

import com.android.test.transition.demo.R;
import com.android.test.transition.demo.transitions.ChangeTextTransition;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.TransitionSet;
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

        /**
         * 不生效?
         */
        /*TransitionSet transitions = new TransitionSet();
        transitions.setDuration(3000)
            .addTransition(new ChangeBounds())
            .addTransition(new ChangeTextTransition());

        getWindow().setSharedElementExitTransition(transitions);*/


        findViewById(R.id.id_btn_start_B).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityB();
            }
        });

        /**
         * ExitSharedElementCallback()在ShareElementTransitionA启动ShareElementTransitionB时,触发A.exit()
         *
         * 此处的回调时序分两种情况：A -> B(start activity), B -> A(back)
         *
         * 1.A -> B
         *
         * 11-19 17:54:26.572 I/A       ( 9122): onMapSharedElements()
         * 11-19 17:54:26.594 I/A       ( 9122): onCaptureSharedElementSnapshot()
         * 11-19 17:54:26.682 I/A       ( 9122): onSharedElementsArrived()
         *
         *
         * 2.B -> A
         *
         * 11-19 17:55:10.950 I/A       ( 9122): onMapSharedElements()
         * 11-19 17:55:10.951 I/A       ( 9122): onCaptureSharedElementSnapshot()
         * 11-19 17:55:11.999 I/A       ( 9122): onSharedElementsArrived()
         * 11-19 17:55:12.013 I/A       ( 9122): onRejectSharedElements()
         * 11-19 17:55:12.014 I/A       ( 9122): onCreateSnapshotView()
         * 11-19 17:55:12.016 I/A       ( 9122): onSharedElementStart()
         * 11-19 17:55:12.028 I/A       ( 9122): onSharedElementEnd()
         *
         */
        setExitSharedElementCallback(new MySharedElementCallback("A"));
    }

    private void startActivityB() {
        Intent intent = new Intent(this, ShareElementTransitionB.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
            new Pair<View, String>(mShareElementView, "shared_view_01"));
        startActivity(intent, options.toBundle());
    }
}
