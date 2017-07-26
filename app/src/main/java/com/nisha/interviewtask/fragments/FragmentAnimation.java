package com.nisha.interviewtask.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.Arrays;
import java.util.List;

import com.nisha.interviewtask.R;

public class FragmentAnimation extends Fragment {


    public SlidingUpPanelLayout mLayout;

    public ListView lv;
    private TextView fadeTxt;
    private TextView main;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(
                R.layout.fragment_fragment_animation, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setIntialValues();

    }

    private void setIntialValues() {
        main=(TextView)getView().findViewById(R.id.main);
        mLayout=(SlidingUpPanelLayout)getView().findViewById(R.id.sliding_layout);
        lv=(ListView)getView().findViewById(R.id.list);
        fadeTxt=(TextView)getView().findViewById(R.id.fadeTxt);


        List<String> your_array_list = Arrays.asList(
                "This",
                "Is",
                "An",
                "Example",
                "ListView",
                "That",
                "You",
                "Can",
                "Scroll",
                ".",
                "It",
                "Shows",
                "How",
                "Any",
                "Scrollable",
                "View",
                "Can",
                "Be",
                "Included",
                "As",
                "A",
                "Child",
                "Of",
                "SlidingUpPanelLayout"
        );

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                your_array_list );

        lv.setAdapter(arrayAdapter);

        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i("", "onPanelSlide, offset " + slideOffset);

                showViewAnimation(lv);
                hideViewAnimation(fadeTxt);
                hideViewAnimation(main);
                //  lv.setVisibility(View.VISIBLE);
                //fadeTxt.setVisibility(View.GONE);
            }


            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState,
                                            SlidingUpPanelLayout.PanelState newState) {
                Log.i("", "onPanelStateChanged " + newState);
                if(newState==SlidingUpPanelLayout.PanelState.COLLAPSED){
                    showViewAnimation(fadeTxt);
                    showViewAnimation(main);
                    hideViewAnimation(lv);
                    //fadeTxt.setVisibility(View.VISIBLE);
                    //lv.setVisibility(View.GONE);
                }else if(newState==SlidingUpPanelLayout.PanelState.DRAGGING){
                    showViewAnimation(fadeTxt);
                    showViewAnimation(main);
                    hideViewAnimation(lv);
                }
            }
        });


        mLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    public void hideViewAnimation(final View v){
        v.animate()
                .alpha(0.0f)
                .translationY(v.getHeight())
                .setDuration(10)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        v.setVisibility(View.GONE);
                    }
                });
    }

    public void showViewAnimation(final View v){
        v.animate()
                .alpha(1.0f)
                .translationY(0)
                .setDuration(10)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        v.setVisibility(View.VISIBLE);
                    }
                });
    }

}
