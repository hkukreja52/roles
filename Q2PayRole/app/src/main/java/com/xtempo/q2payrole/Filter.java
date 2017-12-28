package com.xtempo.q2payrole;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xpentra.adapterlibrary.SpinnerAdapter;


public class Filter extends BottomSheetDialogFragment {
    ArrayList<Corporate> list;
    ArrayList<User> aList;
    @BindView(R.id.btn_corporate)
    protected Button Btn_Corporate;
    @BindView(R.id.btn_user)
    protected Button Btn_User;
    @BindView(R.id.applyfilter)
    protected Button ApplyFilter;
    @BindView(R.id.clearfilter)
    protected Button ClearFilter;
    Corporate corporate;
    User user;
    String corp_id, user_id;


    public interface callbacks {
        void FilterApplied(String corp_id, String user_id);
    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.filter, null);
        dialog.setContentView(contentView);
        ButterKnife.bind(this, contentView);
        viewCorporates();
        viewUsers();
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }

    @OnClick(R.id.btn_corporate)
    public void showCorporate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final SpinnerAdapter<Corporate> spinnerAdapter = new SpinnerAdapter<>(getContext(), R.layout.spinner_items, com.xtempo.q2payrole.BR.model);
        spinnerAdapter.updateList(list);
        builder.setAdapter(spinnerAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                corporate = spinnerAdapter.getItem(i);
                corp_id = corporate.getCorp_id();
                Btn_Corporate.setText(corporate.getCorp_name());


            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @OnClick(R.id.btn_user)
    public void showUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final SpinnerAdapter<User> userSpinnerAdapter = new SpinnerAdapter<>(getContext(), R.layout.cardlist, com.xtempo.q2payrole.BR.MyHandler);
        userSpinnerAdapter.updateList(aList);
        builder.setAdapter(userSpinnerAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                user = userSpinnerAdapter.getItem(i);
                user_id = user.getUser_id();
                Btn_User.setText(user.getFirst_name() + " " + user.getLast_name());

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @OnClick(R.id.applyfilter)
    public void onApplyFilter() {

        String corp = Btn_Corporate.getText().toString().trim();
        String users = Btn_User.getText().toString().trim();
        if (!corp.equalsIgnoreCase(getString(R.string.bs_gallery))) {
            Btn_Corporate.setText(corp);
        }

        if (!users.equalsIgnoreCase(getString(R.string.bs_camera))) {
            Btn_User.setText(users);
        }
        dismiss();
        if (getActivity() instanceof callbacks)
            ((callbacks) getActivity()).FilterApplied(corp_id, user_id);
    }

    @OnClick(R.id.clearfilter)
    public void onClearFilter() {
        String corp = Btn_Corporate.getText().toString().trim();
        String users = Btn_User.getText().toString().trim();
        dismiss();
    }

    public void viewCorporates() {
        Corporate corporate = new Corporate();
        NetworkManager.view_all_corporates(corporate);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResult(CorporateResponse corporateResponse) {
        if (!corporateResponse.hasErrors()) {
            list = corporateResponse.getCorporates();
        }
    }

    public void viewUsers() {
        UserReq userReq = new UserReq();
        NetworkManager.ViewUsers(userReq);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResultApi(UserResponse userResponse) {
        if (!userResponse.hasErrors()) {
            aList = userResponse.getUser();
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
}
