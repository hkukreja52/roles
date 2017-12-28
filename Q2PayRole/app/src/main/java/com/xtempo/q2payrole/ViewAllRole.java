package com.xtempo.q2payrole;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import xpentra.adapterlibrary.CardAdapter;

public class ViewAllRole extends Q2PAYFRAGMENT implements CardAdapter.Callbacks<Role>, Filter.callbacks {
    ArrayList<Role> role;
    @BindView(R.id.recycler)
    protected RecyclerView recyclerView;
    @BindView(R.id.action_button)
    protected FloatingActionButton fab;
    private static final String EXTRA_USER_ID = "user_id";
    private static final String EXTRA_CORP_ID = "corp_id";

    @Override
    public void cardAdapterSelectedItem(Role role) {
        String role_id = role.getRole_id();
        String corp_id = role.getCorp_id();
        ((Callback) getActivity()).onEditRole(role_id, corp_id, role);
    }

    @Override
    public void FilterApplied(String corp_id, String user_id) {
        RoleRequest rolerequest = new RoleRequest();
        rolerequest.setUser_id(user_id);
        rolerequest.setCorp_id(corp_id);
        NetworkManager.ViewAllRole(rolerequest);

    }

    public interface Callbacks {
        void onRoleSelected();
    }

    public interface Callback {
        void onEditRole(String role_id, String corp_id, Role role);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_all_role, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CardAdapter<Role> adapter = new CardAdapter<>(R.layout.list_card, com.xtempo.q2payrole.BR.entry);
        adapter.setCallbacks(this);
        recyclerView.setAdapter(adapter);
        rolesrequest();
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ((Callbacks) getActivity()).onRoleSelected();
            }
        });

    }

    public void rolesrequest() {
        RoleRequest rolerequest = new RoleRequest();

        NetworkManager.ViewAllRole(rolerequest);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onApiResult(RoleResponse roleresponse) {
        if (!roleresponse.hasErrors()) {
            role = roleresponse.getRoles();
            ((CardAdapter<Role>) recyclerView.getAdapter()).setList(role);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private String getCorp_id() {
        return getArguments().getString(EXTRA_CORP_ID);
    }

    private String getUser_id() {
        return getArguments().getString(EXTRA_USER_ID);
    }

}