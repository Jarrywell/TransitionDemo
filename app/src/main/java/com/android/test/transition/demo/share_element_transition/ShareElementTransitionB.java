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

        /**
         * EnterSharedElementCallback()在ShareElementTransitionB启动时触发B.enter()
         *
         * 此处的回调时序分两种情况：A -> B(start activity), B -> A(back)
         *
         * 1.A -> B
         *
         * 11-19 17:56:46.090 I/B       ( 9122): onMapSharedElements()
         * 11-19 17:56:46.091 I/B       ( 9122): onSharedElementsArrived()
         * 11-19 17:56:46.097 I/B       ( 9122): onRejectSharedElements()
         * 11-19 17:56:46.097 I/B       ( 9122): onCreateSnapshotView()
         * 11-19 17:56:46.098 I/B       ( 9122): onSharedElementStart()
         * 11-19 17:56:46.103 I/B       ( 9122): onSharedElementEnd()
         *
         *
         * 2.B -> A
         *
         * 11-19 17:57:23.341 I/B       ( 9122): onMapSharedElements()
         * 11-19 17:57:23.373 I/B       ( 9122): onCreateSnapshotView()
         * 11-19 17:57:23.374 I/B       ( 9122): onSharedElementEnd()  --这里两个顺序是倒的!!
         * 11-19 17:57:23.381 I/B       ( 9122): onSharedElementStart()
         * 11-19 17:57:24.400 I/B       ( 9122): onSharedElementsArrived()
         *
         */
        setEnterSharedElementCallback(new MySharedElementCallback("B"));
    }
}
