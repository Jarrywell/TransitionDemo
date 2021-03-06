package com.android.test.transition.demo.fragment;

import com.android.test.transition.demo.R;
import com.android.test.transition.demo.share_element_transition.MySharedElementCallback;
import com.android.test.transition.demo.transitions.ChangeBackgroundTransition;
import com.android.test.transition.demo.transitions.ChangeTextTransition;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * des:
 * author: libingyan
 * Date: 18-11-13 20:45
 */
public class TransitionFragment1 extends Fragment {

    private View mShareElementView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_share_element_transition_a, container, false);

        mShareElementView = view.findViewById(R.id.id_btn_start_B);

        mShareElementView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment2 = new TransitionFragment2();

                TransitionSet transitions = new TransitionSet();
                transitions.setDuration(1000)
                    .addTransition(new ChangeBounds())
                    .addTransition(new ChangeTextTransition())
                    .addTransition(new ChangeBackgroundTransition());

                fragment2.setSharedElementEnterTransition(transitions);
                fragment2.setEnterTransition(new Slide(Gravity.RIGHT));

                getFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.activity_fragment_container, fragment2)
                    .addSharedElement(mShareElementView, "shared_view_01")
                    .commit();
            }
        });

        /**
         * 同ShareElementTransitionA,但回调函数有差异
         */
        setExitSharedElementCallback(new MySharedElementCallback("Fragment1"));

        return view;
    }
}
