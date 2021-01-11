package com.aperitivoexpress.fooddelivery.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.aperitivoexpress.fooddelivery.BaseActivity;
import com.aperitivoexpress.fooddelivery.R;
import com.aperitivoexpress.fooddelivery.models.QRInfo;
import com.aperitivoexpress.fooddelivery.rest.ApiClient;
import com.aperitivoexpress.fooddelivery.rest.ApiInterface;
import com.aperitivoexpress.fooddelivery.utils.Const;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.thekhaeng.pushdownanim.PushDownAnim;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScannerActivity extends BaseActivity {
    private static final String TAG = "HowWorkActivity";

    @BindView(R.id.ll_scannow)
    LinearLayout llScannow;

    private Call<QRInfo> QRCall;
    private ApiInterface apiService;
    private Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        apiService = ApiClient.getClient().create(ApiInterface.class);

        progressDialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_progress, null);
        progressDialog.setCancelable(false);
        progressDialog.setContentView(view);

        if (progressDialog.getWindow() != null)
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        PushDownAnim
                .setPushDownAnimTo(llScannow)
                .setScale(0.8f)
                .setDurationPush(50)
                .setDurationRelease(50)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new IntentIntegrator(ScannerActivity.this).setPrompt("Scan QR Code Now").initiateScan();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Sorry, You cancelled the Scanning!", Toast.LENGTH_LONG).show();
            } else {
                if (commonUtils.isNetworkAvailable()) {
                    progressDialog.show();

                    QRCall = apiService.getQRInfo(result.getContents());
                    QRCall.enqueue(new Callback<QRInfo>() {
                        @Override
                        public void onResponse(Call<QRInfo> call, Response<QRInfo> response) {
                            progressDialog.dismiss();

                            if (response.isSuccessful()) {
                                QRInfo qrInfo = response.body();
                                if (qrInfo.status) {
                                    Intent intent = new Intent(ScannerActivity.this, DetailsActivity.class);
                                    intent.putExtra(Const.QRInfo, new Gson().toJson(qrInfo.data));
                                    startActivity(intent);
                                } else {
                                    dialogInvalidQR(qrInfo.message);
                                }
                            } else {
                                dialogInvalidQR("This QR Code is Not Matching in our System!");
                            }
                        }

                        @Override
                        public void onFailure(Call<QRInfo> call, Throwable t) {
                            progressDialog.dismiss();
                            dialogInvalidQR("This QR Code is Not Matching in our System!");
                        }
                    });
                } else {
                    Toast.makeText(this, "Sorry, No Internet Connection!", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void dialogInvalidQR(String msg) {
        AlertDialog dataDialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_invalid_qr, null);
        builder.setView(dialogView);

        dataDialog = builder.create();
        TextView txtMes, txtOkay;
        txtMes = dialogView.findViewById(R.id.txt_message);
        txtOkay = dialogView.findViewById(R.id.txt_okay);

        txtMes.setText(msg);
        AlertDialog finalDataDialog = dataDialog;
        txtOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalDataDialog.dismiss();
            }
        });

        if (finalDataDialog != null)
            finalDataDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        dataDialog.show();
    }
}