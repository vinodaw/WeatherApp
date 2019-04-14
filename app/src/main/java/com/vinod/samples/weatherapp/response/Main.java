package com.vinod.samples.weatherapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    @Expose
    public Double temp;
    @SerializedName("pressure")
    @Expose
    public Integer pressure;
    @SerializedName("humidity")
    @Expose
    public Integer humidity;
    @SerializedName("temp_min")
    @Expose
    public Integer tempMin;
    @SerializedName("temp_max")
    @Expose
    public Double tempMax;

    public Double getTemp() {
        return temp;
    }

    public Integer getPressure() {
        return pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Integer getTempMin() {
        return tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }
}