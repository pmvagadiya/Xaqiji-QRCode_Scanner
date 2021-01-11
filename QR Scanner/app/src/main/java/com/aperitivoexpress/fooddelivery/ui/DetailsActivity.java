package com.aperitivoexpress.fooddelivery.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.aperitivoexpress.fooddelivery.BaseActivity;
import com.aperitivoexpress.fooddelivery.R;
import com.aperitivoexpress.fooddelivery.models.Data;
import com.aperitivoexpress.fooddelivery.utils.Const;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends BaseActivity {
    private static final String TAG = "HowWorkActivity";
    @BindView(R.id.img_logo)
    ImageView imgLogo;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_age)
    TextView txtAge;
    @BindView(R.id.txt_sex)
    TextView txtSex;
    @BindView(R.id.txt_address)
    TextView txtAddress;
    @BindView(R.id.txt_location)
    TextView txtLocation;
    @BindView(R.id.txt_telephone)
    TextView txtTelephone;
    @BindView(R.id.txt_msg)
    TextView txtMsg;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.txt_passport)
    TextView txtPassport;

    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        if (getIntent().getExtras() != null) {
            String qrInfo = getIntent().getStringExtra(Const.QRInfo);
            data = new Gson().fromJson(qrInfo, Data.class);

            RequestOptions requestOption = new RequestOptions()
                    .placeholder(R.drawable.img_placeholder)
                    .error(R.drawable.img_placeholder);

            Glide.with(DetailsActivity.this)
                    .load(data.logo)
                    .apply(requestOption)
                    .into(imgLogo);

            txtName.setText(": " + data.name);
            txtAge.setText(": " + data.age);
            txtSex.setText(": " + data.sex);
            txtAddress.setText(": " + data.address);
            txtLocation.setText(": " + data.location);
            txtTelephone.setText(": " + data.telephone);
            txtMsg.setText(": " + data.message);
            txtPassport.setText(": " + data.passport);
        }
    }

    @OnClick(R.id.iv_left)
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}