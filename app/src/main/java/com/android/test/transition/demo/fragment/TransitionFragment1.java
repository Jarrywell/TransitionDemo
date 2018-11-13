package com.android.test.transition.demo.fragment;

import com.android.test.transition.demo.R;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.ChangeBounds;
import android.support.transition.Slide;
import android.support.v4.app.Fragment;
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
                fragment2.setSharedElementEnterTransition(new ChangeBounds());
                fragment2.setEnterTransition(new Slide(Gravity.RIGHT));

                getFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.activity_fragment_container, fragment2)
                    .addSharedElement(mShareElementView, "shared_view_01")
                    .commit();
            }
        });

        return view;
    }
}
