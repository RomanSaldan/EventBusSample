package com.lynx.ebsample2.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.lynx.ebsample2.R;
import com.lynx.ebsample2.events.ImageEvent;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by WORK on 20.10.2015.
 */
public class FragmentImage extends Fragment {

    @Bind(R.id.ivOut_FI)
    protected ImageView ivOut_FI;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void onEventMainThread(ImageEvent eventNow) {
        Picasso.with(getActivity())
                .load(eventNow.getUrl())
                .fit()
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(ivOut_FI, new Callback() {
                    @Override
                    public void onSuccess() {
                        // get the center for the clipping circle
                        int cx = ivOut_FI.getLeft();
                        int cy = ivOut_FI.getBottom();

                        // get the final radius for the clipping circle
                        int dx = Math.max(cx, ivOut_FI.getWidth() - cx);
                        int dy = Math.max(cy, ivOut_FI.getHeight() - cy);
                        float finalRadius = (float) Math.hypot(dx, dy);

                        SupportAnimator animator =
                                ViewAnimationUtils.createCircularReveal(ivOut_FI, cx, cy, 0, finalRadius);
                        animator.setInterpolator(new AccelerateDecelerateInterpolator());
                        animator.setDuration(1000);
                        animator.start();
                    }

                    @Override
                    public void onError() {
                    }
                });
    }


}
