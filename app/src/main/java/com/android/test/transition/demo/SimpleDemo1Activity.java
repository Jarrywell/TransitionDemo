package com.android.test.transition.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.Transition;
import android.support.transition.TransitionInflater;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

/**
 * des:
 * author: libingyan
 * Date: 18-11-13 11:14
 */
public class SimpleDemo1Activity extends AppCompatActivity implements View.OnClickListener{

    private ViewGroup mSceneRoot;
    private View mTagetView1, mTagetView2, mTagetView3, mTagetView4;
    private Transition mTransition;
    private boolean mIsBig = false;
    private int mPrimarySize = 150;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple_demo1);

        mSceneRoot = findViewById(R.id.activity_simple_demo1);

        mTagetView1 = findViewById(R.id.btn_target_view_1);
        mTagetView1.setOnClickListener(this);

        mTagetView2 = findViewById(R.id.btn_target_view_2);
        mTagetView2.setOnClickListener(this);

        mTagetView3 = findViewById(R.id.btn_target_view_3);
        mTagetView3.setOnClickListener(this);

        mTagetView4 = findViewById(R.id.btn_target_view_4);
        mTagetView4.setOnClickListener(this);

        mTransition = TransitionInflater.from(this)
            .inflateTransition(R.transition.changebounds_and_explode);
    }

    @Override
    public void onClick(View v) {

        TransitionManager.beginDelayedTransition(mSceneRoot, mTransition);

        /**
         * 通过代码改变view的属性来触发场景动画的计算
         */
        changeScene(v);
    }

    private void changeScene(View view) {

        changeSize(view);

        changeVisibility(mTagetView1, mTagetView2, mTagetView3, mTagetView4);

        view.setVisibility(View.VISIBLE);
    }

    private void changeSize(View view) {
        mIsBig = !mIsBig;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (mIsBig) {
            layoutParams.width = (int) (3 * mPrimarySize);
            layoutParams.height = (int) (3 * mPrimarySize);
        } else {
            layoutParams.width = mPrimarySize;
            layoutParams.height = mPrimarySize;
        }
        view.setLayoutParams(layoutParams);
    }

    private void changeVisibility(View... views) {
        for (View view : views) {
            view.setVisibility(view.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
        }
    }
}
