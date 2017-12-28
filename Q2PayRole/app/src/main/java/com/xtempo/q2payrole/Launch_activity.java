package com.xtempo.q2payrole;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

public class Launch_activity extends AppCompatActivity implements SplashFragment.Callbacks {
    private static final String TAG_SPLASH = "splash";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity);
        showSplashFragment();

    }

    private void showSplashFragment() {

        Fragment fragment = new SplashFragment();
        changeFragment(fragment, TAG_SPLASH, false);
    }

    private void changeFragment(Fragment fragment, String tag, boolean addToBackStack) {
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();


        View loader = findViewById(R.id.loader);
        if (loader != null && loader.getTransitionName() != null) {
            transaction.addSharedElement(loader, loader.getTransitionName());
        }

        if (addToBackStack)
            transaction.addToBackStack(null);

        transaction.replace(R.id.content, fragment, tag);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void splashFragmentComplete() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}
