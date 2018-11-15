package com.android.test.transition.demo;

import com.android.test.transition.demo.transitions.ChangeBackgroundTransition;
import com.android.test.transition.demo.transitions.ChangeTextTransition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * des:
 * author: libingyan
 * Date: 18-11-15 19:12
 */
public class MyTransitionActivity extends AppCompatActivity {

    private ViewGroup mSceneView;
    private TextView mTargetView1;
    private int mIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_transition);

        mSceneView = findViewById(R.id.id_target_parent);
        mTargetView1 = findViewById(R.id.id_target_view);

        /**
         * ChangeTextTransition
         */
        findViewById(R.id.btn_test_text_transition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * 自定义Transition
                 */
                Transition changeTextTransition = new ChangeTextTransition();
                changeTextTransition.setDuration(200);
                TransitionManager.beginDelayedTransition(mSceneView, changeTextTransition);
                if (mIndex % 2 == 0) {
                    mTargetView1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
                    mTargetView1.setTextColor(0xff0000ff);
                } else {
                    mTargetView1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
                    mTargetView1.setTextColor(0xffff0000);
                }

                /**
                 * XML中的自定义Transition 跑不起来!!不支持在xml中使用自定义transition
                 */
                /*Transition transition = TransitionInflater.from(MyTransitionActivity.this)
                    .inflateTransition(R.transition.test_transition);

                TransitionManager.beginDelayedTransition(mSceneView, transition);
                if (mIndex % 2 == 0) {
                    mTargetView1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
                    mTargetView1.setTextColor(0xff0000ff);
                } else {
                    mTargetView1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
                    mTargetView1.setTextColor(0xffff0000);
                }*/


                mIndex++;
            }
        });

        findViewById(R.id.btn_test_background_transition).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Transition changeBackgroundTransition = new ChangeBackgroundTransition();
                    changeBackgroundTransition.setDuration(500);
                    TransitionManager.beginDelayedTransition(mSceneView, changeBackgroundTransition);
                    if (mIndex % 2 == 0) {
                        mTargetView1.setBackgroundColor(0xff00ffff);
                    } else {
                        mTargetView1.setBackgroundColor(0xffffff00);
                    }
                    mIndex++;
                }
            });

    }
}
