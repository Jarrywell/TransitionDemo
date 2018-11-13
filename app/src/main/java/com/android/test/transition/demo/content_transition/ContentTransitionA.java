package com.android.test.transition.demo.content_transition;

import com.android.test.transition.demo.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionInflater;
import android.view.View;

/**
 * des:
 * author: libingyan
 * Date: 18-11-13 14:12
 */
public class ContentTransitionA extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_transition_a);

        /**
         * 触发场景：MainActivity->ContentTransitionA (startActivity)
         * A.EnterTransition()
         */
        getWindow().setEnterTransition(TransitionInflater.from(this)
            .inflateTransition(R.transition.content_transition_a_enter_bottom));

        /**
         * 触发场景：ContentTransitionA -> ContentTransitionB (startActivity)
         * A.ExitTransition() -> B.EnterTransition()
         */
        getWindow().setExitTransition(TransitionInflater.from(this)
            .inflateTransition(R.transition.content_transition_a_exit_top));

        /**
         * 触发场景：ContentTransitionB -> ContentTransitionA (Back)
         * B.ReturnTransition() -> A.ReenterTransition()
         */
        getWindow().setReenterTransition(TransitionInflater.from(this)
            .inflateTransition(R.transition.content_transition_a_reenter_right));


        /**
         * 触发场景：ContentTransitionA -> MainActivity (Back)
         * A.ReturnTransition()
         */
        getWindow().setReturnTransition(TransitionInflater.from(this)
            .inflateTransition(R.transition.content_transition_a_return_left));



        findViewById(R.id.id_btn_start_B).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityB();
            }
        });
    }

    private void startActivityB() {
        Intent intent = new Intent(this, ContentTransitionB.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        startActivity(intent, options.toBundle());
    }
}
