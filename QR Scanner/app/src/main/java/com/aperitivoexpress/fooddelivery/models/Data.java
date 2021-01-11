
package com.aperitivoexpress.fooddelivery.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("qrcode_id")
    @Expose
    public String qrcodeId;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("qrcode_name")
    @Expose
    public String qrcodeName;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("birthdate")
    @Expose
    public String age;
    @SerializedName("sex")
    @Expose
    public String sex;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("location")
    @Expose
    public String location;
    @SerializedName("telephone")
    @Expose
    public String telephone;
    @SerializedName("passport")
    @Expose
    public String passport;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("logo")
    @Expose
    public String logo;
    @SerializedName("qrcode_image")
    @Expose
    public String qrcodeImage;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
}
