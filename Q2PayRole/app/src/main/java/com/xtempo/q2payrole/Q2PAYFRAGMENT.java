package com.xtempo.q2payrole;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;


public class Q2PAYFRAGMENT extends AppCompatDialogFragment {
    private boolean hideKeyboard;

    public Q2PAYFRAGMENT() {
        setArguments(new Bundle());
        hideKeyboard = true;

    }

    @Override
    public int getTheme() {
        return R.style.Dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        if (view != null)
            ButterKnife.bind(this, view);
    }

    @Override
    public void onResume() {
        super.onResume();

        View keyboardFocus = getActivity().getCurrentFocus();
        if (hideKeyboard && keyboardFocus != null) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(keyboardFocus.getWindowToken(), 0);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        if (getTargetFragment() != null)
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, null);
    }

    public boolean onBackPressed() {
        return false;
    }

    protected void setSupportActionBar(Toolbar toolbar) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    protected void hideKeyboardOnResume(boolean hide) {
        hideKeyboard = hide;
    }

    protected void showDialog(DialogFragment dialogFragment, int requestCode, String tag) {
        boolean shouldShow = getChildFragmentManager().findFragmentByTag(tag) == null;

        if (shouldShow) {
            dialogFragment.setTargetFragment(this, requestCode);
            dialogFragment.show(getChildFragmentManager(), tag);
        }
    }

    protected void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setTitle(R.string.update_title);
        builder.setMessage(R.string.update_message);
        builder.setPositiveButton(R.string.update, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                openPlayStoreListing();
            }
        });
        builder.show();
    }

    protected void openPlayStoreListing() {
        String packageName = getContext().getPackageName();
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
        } catch (Throwable e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
        }
    }
}



