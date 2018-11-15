package com.android.test.transition.demo.fragment;

import com.android.test.transition.demo.R;
import com.android.test.transition.demo.share_element_transition.MySharedElementCallback;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * des:
 * author: libingyan
 * Date: 18-11-13 20:52
 */
public class TransitionFragment2 extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 同ShareElementTransitionB,但回调函数有差异
         */
        setEnterSharedElementCallback(new MySharedElementCallback("Fragment2"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_share_element_transition_b, container, false);
    }
}
