package com.aperitivoexpress.fooddelivery;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.aperitivoexpress.fooddelivery.rest.ApiClient;
import com.aperitivoexpress.fooddelivery.rest.ApiInterface;
import com.aperitivoexpress.fooddelivery.utils.CommonUtils;
import com.aperitivoexpress.fooddelivery.utils.SharePref;


public class BaseActivity extends AppCompatActivity {
    public CommonUtils commonUtils;
    public ProgressDialog progressdialog;
    public SharePref sharePref;
    public ApiInterface apiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBaseview();
    }

    private void initBaseview() {
        apiService = ApiClient.getClient().create(ApiInterface.class);
        
        commonUtils = new CommonUtils(this);
        sharePref = new SharePref(this);

        progressdialog = new ProgressDialog(this);
        progressdialog.setMessage("Please wait...");
        progressdialog.setCancelable(false);
    }

    public void showProgDialog() {
        if (progressdialog != null && !progressdialog.isShowing()) {
            progressdialog.show();
        }
    }

    public void hideProgDialog() {
        if (progressdialog != null && progressdialog.isShowing()) {
            progressdialog.dismiss();
        }
    }
}
