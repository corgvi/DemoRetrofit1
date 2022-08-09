package com.example.demoretrofit.model;

import com.google.gson.annotations.SerializedName;

public class Quote {
    @SerializedName("USDVND")
    private String usdVnd;

    public String getUsdVnd() {
        return usdVnd;
    }

    public void setUsdVnd(String usdVnd) {
        this.usdVnd = usdVnd;
    }

    public Quote(String usdVnd) {
        this.usdVnd = usdVnd;
    }
}
