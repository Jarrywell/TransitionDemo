package com.android.test.transition.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.ChangeBounds;
import android.support.transition.Scene;
import android.support.transition.Transition;
import android.support.transition.TransitionInflater;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

/**
 * des: TransitionManager.go()是根据xml文件创造start scene和end scene（繁琐）
 * author: libingyan
 * Date: 18-11-12 20:25
 */
public class SimpleSceneActivity extends AppCompatActivity {

    private final String TAG = "SimpleSceneActivity";

    private ViewGroup mSenceRoot;
    private boolean mIsSence;
    private Scene mScene1, mScene2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_scene);

        mSenceRoot = findViewById(R.id.scene_root);

        /**
         * 初始场景1(场景就是对界面的布局文件)
         */
        mScene1 = Scene.getSceneForLayout(mSenceRoot, R.layout.layout_simple_scene1, this);

        /**
         *目标场景2(布局文件)
         */
        mScene2 = Scene.getSceneForLayout(mSenceRoot, R.layout.layout_simple_scene2, this);

        /**
         * 使用默认的transition（AutoTransition）切换到场景1
         *
         */
        TransitionManager.go(mScene1);


        findViewById(R.id.btn_test_01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 代码创建transition
                 */
                /*ChangeBounds transition = new ChangeBounds();
                transition.setDuration(500);*/

                /**
                 * 使用布局创建transition
                 */
                Transition transition = TransitionInflater.from(SimpleSceneActivity.this)
                    .inflateTransition(R.transition.changebounds_and_fade);

                /**
                 * 使用ChangeBounds类型的transition切换场景
                 * 注意：我在场景2中放置了一个在场景1中没有的view(btn_target_view_5)，此时该view没有动画
                 *
                 * TransitionManager.go()原理逻辑：调用当前scene.exit()，接着调用下一个scene.enter(),
                 * 还会触发当前场景scene.setExitAction()，下一个场景的scene.setEnterAction()
                 */
                TransitionManager.go(mIsSence ? mScene1 : mScene2, transition);
                mIsSence = !mIsSence;
            }
        });
    }
}
