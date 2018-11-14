package com.android.test.transition.demo.share_element_transition;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Parcelable;
import android.support.v4.app.SharedElementCallback;
import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.Map;

/**
 * des:
 * author: libingyan
 * Date: 18-11-14 17:39
 *
 * 此处的回调时序分两种情况：A -> B(start activity), B -> A(back)
 *
 * 1.A -> B
 *
 * 11-19 17:59:06.578 I/A       ( 9122): onMapSharedElements()
 * 11-19 17:59:06.603 I/A       ( 9122): onCaptureSharedElementSnapshot()
 * 11-19 17:59:06.683 I/A       ( 9122): onSharedElementsArrived()
 * 11-19 17:59:06.710 I/B       ( 9122): onMapSharedElements()
 * 11-19 17:59:06.711 I/B       ( 9122): onSharedElementsArrived()
 * 11-19 17:59:06.718 I/B       ( 9122): onRejectSharedElements()
 * 11-19 17:59:06.718 I/B       ( 9122): onCreateSnapshotView()
 * 11-19 17:59:06.719 I/B       ( 9122): onSharedElementStart()
 * 11-19 17:59:06.724 I/B       ( 9122): onSharedElementEnd()
 *
 *
 * 2.B -> A
 *
 * 11-19 17:59:32.396 I/B       ( 9122): onMapSharedElements()
 * 11-19 17:59:32.411 I/A       ( 9122): onMapSharedElements()
 * 11-19 17:59:32.412 I/A       ( 9122): onCaptureSharedElementSnapshot()
 * 11-19 17:59:32.436 I/B       ( 9122): onCreateSnapshotView()
 * 11-19 17:59:32.437 I/B       ( 9122): onSharedElementEnd() --这里两个顺序是倒的!!
 * 11-19 17:59:32.439 I/B       ( 9122): onSharedElementStart()
 * 11-19 17:59:33.451 I/B       ( 9122): onSharedElementsArrived()
 * 11-19 17:59:33.456 I/A       ( 9122): onSharedElementsArrived()
 * 11-19 17:59:33.468 I/A       ( 9122): onRejectSharedElements()
 * 11-19 17:59:33.468 I/A       ( 9122): onCreateSnapshotView()
 * 11-19 17:59:33.471 I/A       ( 9122): onSharedElementStart()
 * 11-19 17:59:33.486 I/A       ( 9122): onSharedElementEnd()
 *
 */
public class MySharedElementCallback extends SharedElementCallback {

    private String mTag;

    public MySharedElementCallback(String tag) {
        mTag = tag;
    }

    /**
     * 最先调用，用于动画开始前替换ShareElements，比如在Activity B翻过若干页大图之后，返回Activity A
     * 的时候需要缩小回到对应的小图，就需要在这里进行替换
     * @param names
     * @param sharedElements
     */
    @Override
    public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
        super.onMapSharedElements(names, sharedElements);
        Log.i(mTag, "onMapSharedElements()");
    }


    /**
     * 表示ShareElement已经全部就位，可以开始动画了
     * @param sharedElementNames
     * @param sharedElements
     * @param listener
     */
    @Override
    public void onSharedElementsArrived(List<String> sharedElementNames, List<View> sharedElements,
        OnSharedElementsReadyListener listener) {
        super.onSharedElementsArrived(sharedElementNames, sharedElements, listener);
        Log.i(mTag, "onSharedElementsArrived()");
    }


    /**
     * 在之前的步骤里(onMapSharedElements)被从ShareElements列表里除掉的View会在此回调，
     * 不处理的话默认进行alpha动画消失
     * @param rejectedSharedElements
     */
    @Override
    public void onRejectSharedElements(List<View> rejectedSharedElements) {
        super.onRejectSharedElements(rejectedSharedElements);
        Log.i(mTag, "onRejectSharedElements()");
    }


    /**
     * 在这里会把ShareElement里值得记录的信息存到为Parcelable格式，以发送到Activity B
     * 默认处理规则是ImageView会特殊记录Bitmap、ScaleType、Matrix，其它View只记录大小和位置
     * @param sharedElement
     * @param viewToGlobalMatrix
     * @param screenBounds
     * @return
     */
    @Override
    public Parcelable onCaptureSharedElementSnapshot(View sharedElement, Matrix viewToGlobalMatrix,
        RectF screenBounds) {
        Log.i(mTag, "onCaptureSharedElementSnapshot()");
        return super.onCaptureSharedElementSnapshot(sharedElement, viewToGlobalMatrix, screenBounds);
    }


    /**
     * 在这里会把Activity A传过来的Parcelable数据，重新生成一个View，这个View的大小和位置会与Activity A里的
     * ShareElement一致，
     * @param context
     * @param snapshot
     * @return
     */
    @Override
    public View onCreateSnapshotView(Context context, Parcelable snapshot) {
        Log.i(mTag, "onCreateSnapshotView()");
        return super.onCreateSnapshotView(context, snapshot);
    }


    /**
     *
     * @param sharedElementNames
     * @param sharedElements
     * @param sharedElementSnapshots
     */
    @Override
    public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements,
        List<View> sharedElementSnapshots) {
        super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots);
        Log.i(mTag, "onSharedElementStart()");
    }


    /**
     *
     * @param sharedElementNames
     * @param sharedElements
     * @param sharedElementSnapshots
     */
    @Override
    public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements,
        List<View> sharedElementSnapshots) {
        super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots);
        Log.i(mTag, "onSharedElementEnd()");
    }
}
