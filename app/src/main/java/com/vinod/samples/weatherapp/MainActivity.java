package com.vinod.samples.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.vinod.samples.weatherapp.response.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button btnGo;
    private EditText city;
    private Retrofit retrofit;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnGo = findViewById(R.id.btnGo);
        city = findViewById(R.id.txtCity);
        progressBar = findViewById(R.id.indeterminateBar);

        retrofit = new Retrofit.Builder().baseUrl("https://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create()).build();

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+city.getText());
                progressBar.setVisibility(ProgressBar.VISIBLE);
                Log.d(TAG, "progress is visible: "+progressBar.getVisibility());
                WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);
                Call<WeatherResponse> weatherResponseCall = weatherAPI.getWeatherByCity(city.getText().toString(),"e4bea137dbcbb5e6d7214796e2e23585","metric");

                weatherResponseCall.enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                        Log.d(TAG, "onResponse: "+response.isSuccessful());
                        if(response.isSuccessful()){
                            WeatherResponse weatherResponse = response.body();

                            Log.d(TAG, "onResponse: temp: "+weatherResponse.main.temp);
                            Log.d(TAG, "onResponse: min: "+weatherResponse.main.tempMin);
                            Log.d(TAG, "onResponse: max: "+weatherResponse.main.tempMax);
                            Log.d(TAG, "onResponse: weather: "+weatherResponse.getWeather().get(0).main);

                            Intent intent = new Intent(MainActivity.this,WeatherDetails.class);
                            intent.putExtra("temp",String.valueOf(weatherResponse.main.temp));
                            intent.putExtra("min",String.valueOf(weatherResponse.main.tempMin));
                            intent.putExtra("max",String.valueOf(weatherResponse.main.tempMax));
                            intent.putExtra("weathercond",weatherResponse.getWeather().get(0).main);

                            progressBar.setVisibility(ProgressBar.GONE);

                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure: Weather API call failed "+t.getMessage());
                        progressBar.setVisibility(ProgressBar.GONE);
                    }
                });


            }
        });

      /*  FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    } */
}
