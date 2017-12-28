package com.xtempo.q2payrole;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class UserRoleActivity extends Q2PayActivity implements ViewAllRole.Callback, AddUserRole.callback {
    private static final String TAG_VIEWALLROLE = "viewallrole";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_role);
        ButterKnife.bind(this);
        ViewAllRole();

    }

    private void ViewAllRole() {
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
                Filter filterr = new Filter();
                View BottomSheetView = getLayoutInflater().inflate(R.layout.filter, null);
                filterr.show(getSupportFragmentManager(), "filter");
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
    public void onEditRole(String role_id, String corp_id, Role role) {
        Fragment fragment = new AddUserRole();
        Bundle bundle = new Bundle();
        bundle.putSerializable("role", role);
        bundle.putString("Role_id", role_id);
        bundle.putString("Corp_id", corp_id);
        fragment.setArguments(bundle);
        changeContentFragment(fragment, "adduserrole", false);
    }


    @Override
    public void onAddRoleUser(ArrayList<User> user, String Corp_id, Role role) {
        Fragment fragment = new InsertRoleUser();
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", user);
        bundle.putSerializable("Role", role);
        bundle.putString("Corp_id", Corp_id);
        fragment.setArguments(bundle);
        changeContentFragment(fragment, "insertroleuser", false);
    }
}

