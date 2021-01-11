package com.aperitivoexpress.fooddelivery.rest;

import com.aperitivoexpress.fooddelivery.models.QRInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    // supervisor login
    @FormUrlEncoded
    @POST("match_qr_code.php")
    Call<QRInfo> getQRInfo(@Field("qrCodeData") String qrInfo);
}