package com.aperitivoexpress.fooddelivery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.aperitivoexpress.fooddelivery.BaseActivity;
import com.aperitivoexpress.fooddelivery.R;
import com.aperitivoexpress.fooddelivery.utils.Const;

import butterknife.ButterKnife;

public class StartActivity extends BaseActivity {
    private static final String TAG = "StartActivity";

    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init_view();
    }

    private void init_view() {
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(StartActivity.this, ScannerActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }
        }, Const.SPLASH_TIME_OUT);
    }

    @Override
    protected void onStop() {
        super.onStop();


        if (handler != null)
            handler.removeCallbacks(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (handler != null)
            handler.removeCallbacks(runnable);
    }
}
