package com.vinod.samples.weatherapp;

import com.vinod.samples.weatherapp.response.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {

    @GET("data/2.5/weather")
    Call<WeatherResponse> getWeatherByCity(@Query("q") String city, @Query("appid") String appid,@Query("units") String units);
}
