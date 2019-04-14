package com.vinod.samples.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WeatherDetails extends AppCompatActivity {
    private TextView temp1;
    private TextView minmax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
        temp1 = findViewById(R.id.temp1);
        minmax = findViewById(R.id.minmax);
        String temp = getIntent().getStringExtra("temp");
        String min = getIntent().getStringExtra("min");
        String max = getIntent().getStringExtra("max");

        temp1.setText(temp+(char)0x00B0);
        minmax.setText(min +" / "+max + (char) 0x00B0);
    }
}
