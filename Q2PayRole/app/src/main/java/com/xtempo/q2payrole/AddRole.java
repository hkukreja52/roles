package com.xtempo.q2payrole;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.xtempo.networklibrary.network.data.inherit.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import xpentra.adapterlibrary.SpinnerAdapter;


public class AddRole extends Q2PAYFRAGMENT {
    @BindView(R.id.spinner)
    protected Spinner spinner;
    @BindView(R.id.submit)
    protected Button submit;
    @BindView(R.id.Role)
    protected TextInputEditText roles;
    @BindView(R.id.desc)
    protected TextInputEditText description;


    public void spinner_item() {
        Corporate corporate=new Corporate();
        NetworkManager.view_all_corporates(corporate);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_add_a_new_role, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SpinnerAdapter<Corporate> spinnerAdapter = new SpinnerAdapter<>(getContext(), R.layout.spinner_items, com.xtempo.q2payrole.BR.model);
        spinnerAdapter.setDropDownItemLayout(R.layout.spinner_items, com.xtempo.q2payrole.BR.model);
        spinner.setAdapter(spinnerAdapter);
        spinner_item();

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (roles != null && description != null)
                    Insert_a_Role();

            }
        });
        Role role = getExtraRole();
        if (role != null) {
            roles.setText(role.getRole());
            description.setText(role.getRole_desc());
        }
    }

    public void Insert_a_Role() {
        InsertRoleRequest insertRoleRequest = new InsertRoleRequest();
        NetworkManager.add_a_new_role(insertRoleRequest);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onApiResult(Response response) {
        if (!response.hasErrors()) {

        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResult(CorporateResponse corporateResponse) {
        if (!corporateResponse.hasErrors()) {
            ((SpinnerAdapter<Corporate>) spinner.getAdapter()).updateList(corporateResponse.getCorporates());

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

    private Role getExtraRole() {
        return (Role) getArguments().getSerializable("Role");

    }

}
