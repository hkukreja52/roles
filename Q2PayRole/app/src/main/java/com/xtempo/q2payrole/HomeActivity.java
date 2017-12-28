package com.xtempo.q2payrole;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import butterknife.ButterKnife;

public class HomeActivity extends Q2PayActivity implements ViewAllRole.Callbacks{
    private static final String TAG_VIEWALLROLE = "viewallrole";
    private static final String TAG_FILTER = "filter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        ViewAllRole();
    }

    private void ViewAllRole() {
        Intent intent = new Intent(this, UserRoleActivity.class);
        startActivity(intent);
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_VIEWALLROLE);

        if (fragment == null)
            fragment = new ViewAllRole();

        changeContentFragment(fragment, TAG_VIEWALLROLE, false);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.filter: {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_FILTER);

                if (fragment == null)
                    fragment = new Filter();

                changeContentFragment(fragment, TAG_FILTER, false);
                return true;
            }
            default:
                return super.onOptionsItemSelected(menuItem);

        }
    }

    private void changeContentFragment(@NonNull Fragment fragment, @NonNull String tag, @NonNull boolean addToBackStack) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();


        if (addToBackStack) {
            manager.popBackStack();
            transaction.addToBackStack(null);
            transaction.replace(R.id.content, fragment, tag);
        } else
            transaction.replace(R.id.content, fragment, tag);

        transaction.commit();

    }

    @Override
    public void onBackPressed() {
        Q2PAYFRAGMENT fragment = (Q2PAYFRAGMENT) getSupportFragmentManager().findFragmentById(R.id.content);

        if (fragment != null && fragment.onBackPressed()) {

        } else
            super.onBackPressed();
    }

    @Override
    public void onRoleSelected() {
        Fragment fragment = new AddRole();
        changeContentFragment(fragment, "addrole", false);
    }

}

