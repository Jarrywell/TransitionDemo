package com.android.test.transition.demo;

import com.android.test.transition.demo.content_transition.ContentTransitionA;
import com.android.test.transition.demo.content_transition.ContentTransitionB;
import com.android.test.transition.demo.share_element_transition.ShareElementTransitionA;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_test_transition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SimpleTransitionActivity.class));
            }
        });

        findViewById(R.id.btn_test_scene).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SimpleSceneActivity.class));
            }
        });

        findViewById(R.id.btn_test_transition_demo1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SimpleDemo1Activity.class));
            }
        });

        findViewById(R.id.btn_test_content_transition).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //startActivity(new Intent(MainActivity.this, ContentTransitionA.class));
                    Intent intent = new Intent(MainActivity.this, ContentTransitionA.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this);
                    startActivity(intent, options.toBundle());
                }
            });

        findViewById(R.id.btn_test_share_element_transition).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, ShareElementTransitionA.class));
                }
            });
    }
}
