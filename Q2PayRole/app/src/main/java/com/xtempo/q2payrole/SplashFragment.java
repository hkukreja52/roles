package com.xtempo.q2payrole;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashFragment extends Q2PAYFRAGMENT {
    private static final int ANIM_ROTATION = 360;
    private static final int ANIM_DURATION = 350 * 4;

    public interface Callbacks {
        void splashFragmentComplete();
    }

    @BindView(R.id.loader)
    protected ImageView loader;
    @BindView(R.id.title)
    protected TextView title;
    private boolean loadingComplete = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();

        startRotating();
        loadingComplete = false;
        stopRotating();
    }

    private void startRotating() {
        if (loader != null)
            loader.animate().setDuration(ANIM_DURATION).rotation(loader.getRotation() + ANIM_ROTATION).withEndAction(new Runnable() {
                @Override
                public void run() {
                    if (loadingComplete)
                        stopRotating();
                    else
                        startRotating();
                }
            });
    }

    private void stopRotating() {
        if (loader != null) {
            loader.animate().setDuration(ANIM_DURATION).rotation(loader.getRotation() + ANIM_ROTATION).withEndAction(new Runnable() {
                @Override
                public void run() {
                    loginComplete();
                }
            });
        }
    }

    private void loginComplete() {

        if (getActivity() instanceof Callbacks)
            ((Callbacks) getActivity()).splashFragmentComplete();
    }
}