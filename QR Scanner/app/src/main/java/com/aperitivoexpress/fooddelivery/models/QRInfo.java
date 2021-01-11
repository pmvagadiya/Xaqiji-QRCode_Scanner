
package com.aperitivoexpress.fooddelivery.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QRInfo {

    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public Data data;

}
