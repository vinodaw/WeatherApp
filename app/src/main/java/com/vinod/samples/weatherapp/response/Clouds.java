package com.vinod.samples.weatherapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("all")
    @Expose
    public Integer all;

    public Integer getAll() {
        return all;
    }
}