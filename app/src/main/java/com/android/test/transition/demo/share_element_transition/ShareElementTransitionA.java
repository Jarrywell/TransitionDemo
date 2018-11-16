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
 * activity间动画的回调（在Transition中的captureStartValues()打印的的堆栈）
 *
 * 可以看到其实系统内部还对transition做了处理,而不仅仅是表面上的animator封装
 *
 * at com.android.test.transition.demo.transitions.ChangeTextTransition.captureValues(ChangeTextTransition.java:46)
 * at com.android.test.transition.demo.transitions.ChangeTextTransition.captureStartValues(ChangeTextTransition.java:33)
 * at android.transition.TransitionSet.captureStartValues(TransitionSet.java:443)
 * at android.transition.TransitionSet.captureStartValues(TransitionSet.java:443)
 * at android.transition.TransitionSet.captureStartValues(TransitionSet.java:443)
 * at android.transition.Transition.captureHierarchy(Transition.java:1578)
 * at android.transition.Transition.captureHierarchy(Transition.java:1608)
 * at android.transition.Transition.captureHierarchy(Transition.java:1608)
 * at android.transition.Transition.captureHierarchy(Transition.java:1608)
 * at android.transition.Transition.captureHierarchy(Transition.java:1608)
 * at android.transition.Transition.captureHierarchy(Transition.java:1608)
 * at android.transition.Transition.captureHierarchy(Transition.java:1608)
 * at android.transition.Transition.captureValues(Transition.java:1464)
 * at android.transition.TransitionManager.sceneChangeSetup(TransitionManager.java:324)
 * at android.transition.TransitionManager.beginDelayedTransition(TransitionManager.java:423)
 * at android.app.EnterTransitionCoordinator.beginTransition(EnterTransitionCoordinator.java:548)
 * at android.app.EnterTransitionCoordinator.startSharedElementTransition(EnterTransitionCoordinator.java:397)
 * at android.app.EnterTransitionCoordinator.-wrap4(EnterTransitionCoordinator.java)
 * at android.app.EnterTransitionCoordinator$5$1$1.run(EnterTransitionCoordinator.java:459)
 * at android.app.ActivityTransitionCoordinator.startTransition(ActivityTransitionCoordinator.java:775)
 * at android.app.EnterTransitionCoordinator$5$1.onPreDraw(EnterTransitionCoordinator.java:456)
 * at android.view.ViewTreeObserver.dispatchOnPreDraw(ViewTreeObserver.java:944)
 * at android.view.ViewRootImpl.performTraversals(ViewRootImpl.java:2231)
 * at android.view.ViewRootImpl.doTraversal(ViewRootImpl.java:1272)
 * at android.view.ViewRootImpl$TraversalRunnable.run(ViewRootImpl.java:6445)
 * at android.view.Choreographer$CallbackRecord.run(Choreographer.java:885)
 * at android.view.Choreographer.doCallbacks(Choreographer.java:697)
 * at android.view.Choreographer.doFrame(Choreographer.java:633)
 * at android.view.Choreographer$FrameDisplayEventReceiver.run(Choreographer.java:871)
 * at android.os.Handler.handleCallback(Handler.java:751)
 * at android.os.Handler.dispatchMessage(Handler.java:95)
 * at android.os.Looper.loop(Looper.java:154)
 * at android.app.ActivityThread.main(ActivityThread.java:6348)
 * at java.lang.reflect.Method.invoke(Native Method)
 * at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:920)
 * at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:810)
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
