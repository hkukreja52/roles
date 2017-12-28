package com.xtempo.q2payrole;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import xpentra.adapterlibrary.CardAdapter;


public class AddUserRole extends Q2PAYFRAGMENT {

    @BindView(R.id.recyclerview)
    protected RecyclerView recycle;
    @BindView(R.id.Role2)
    protected TextInputEditText roles;
    @BindView(R.id.desc2)
    protected TextInputEditText description;
    ArrayList<User> list;
    private static final String EXTRA_ROLE_ID = "Role_id";
    private static final String EXTRA_CORP_ID = "Corp_id";


    public interface callback {
        void onAddRoleUser(ArrayList<User> list, String corp_id, Role role2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_add_user_role, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CardAdapter<User> cardAdapter = new CardAdapter<>(R.layout.cardlist, BR.MyHandler);
        recycle.setAdapter(cardAdapter);
        showusers(false);
        Role role = getExtraRole();
        if (role != null) {
            roles.setText(role.getRole());
            description.setText(role.getRole_desc());
        }
    }

    public void showusers(boolean onlycorp) {
        UserReq userReq = new UserReq();
        userReq.setCorp_id(getCorp_id());
        if (onlycorp)
            userReq.setRole_id(getRole_id());
        NetworkManager.ViewUsers(userReq);

    }

    private String getRole_id() {
        return getArguments().getString(EXTRA_ROLE_ID);
    }

    private String getCorp_id() {
        return getArguments().getString(EXTRA_CORP_ID);
    }


    @OnClick(R.id.fab)
    public void onclick() {

        ((callback) getActivity()).onAddRoleUser(list, getCorp_id(), getExtraRole());

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResultApi(UserResponse userResponse) {
        if (!userResponse.hasErrors()) {
            list = userResponse.getUser();
            ((CardAdapter<User>) recycle.getAdapter()).setList(list);
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
        return (Role) getArguments().getSerializable("role");

    }
}