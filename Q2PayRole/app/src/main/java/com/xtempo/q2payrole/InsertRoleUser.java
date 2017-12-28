package com.xtempo.q2payrole;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


public class InsertRoleUser extends Q2PAYFRAGMENT {
    @BindView(R.id.Roles)
    TextInputEditText role;
    AutoCompleteTextView textView;
    User user;
    private static final String EXTRA_CORP_ID = "Corp_id";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_insert_role_user, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Role roles = getExtraRoles();
        if (roles != null) {
            role.setText(roles.getRole());
        }

        final UserAutoCompleteAdapter adapter = new UserAutoCompleteAdapter(getActivity(), R.layout.layout, getExtraUser());
        textView = (AutoCompleteTextView) view.findViewById(R.id.selectuser);
        textView.setThreshold(1);
        textView.setAdapter(adapter);
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object object = adapterView.getItemAtPosition(i);
                if (object instanceof User) {
                    user = (User) object;
                    Log.e(InsertRoleUser.class.getSimpleName(), user.getFirst_name() + " " + user.getLast_name());
                    textView.setText(user.getFirst_name() + " " + user.getLast_name());
                }
            }
        });


    }

    public void insertuserrole() {
        InsertRoleUserRequest insertRoleUserRequest = new InsertRoleUserRequest();
        insertRoleUserRequest.setCorp_id(getCorp_id());
        insertRoleUserRequest.setRole_id(getExtraRoles().getRole_id());
        insertRoleUserRequest.setUser_id(user.getUser_id());
        NetworkManager.InsertUserRole(insertRoleUserRequest);
    }

    @OnClick(R.id.submit2)
    public void onClick() {
        if (role != null && textView != null)
            insertuserrole();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResultApi(UserResponse userResponse) {
        if (!userResponse.hasErrors()) {
        }
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private ArrayList<User> getExtraUser() {
        return (ArrayList<User>) getArguments().getSerializable("User");
    }

    private Role getExtraRoles() {
        return (Role) getArguments().getSerializable("Role");

    }

    private String getCorp_id() {
        return getArguments().getString(EXTRA_CORP_ID);
    }

}
