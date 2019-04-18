package com.vinod.samples.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class WeatherDetails extends AppCompatActivity {
    private TextView temp1;
    private TextView minmax;
    private TextView weathercond;
    private ShareActionProvider shareActionProvider;
    private StringBuilder shareContent = new StringBuilder("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
        temp1 = findViewById(R.id.temp1);
        minmax = findViewById(R.id.minmax);
        weathercond = findViewById(R.id.weather);

        String temp = getIntent().getStringExtra("temp");
        String min = getIntent().getStringExtra("min");
        String max = getIntent().getStringExtra("max");
        String weatherconddesc = getIntent().getStringExtra("weathercond");

        temp1.setText(temp+(char)0x00B0);
        weathercond.setText(weatherconddesc);
        String tempminmax = "High: "+max + (char) 0x00B0 +" Low: "+min +(char) 0x00B0;
        minmax.setText(tempminmax);

        shareContent.append(temp1.getText()).append(weathercond.getText()).append(minmax.getText());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
       /* MenuItem item = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        shareActionProvider.setShareIntent(sendIntent);*/
        return true;
    }

   /* private void setShareIntent(Intent shareIntent) {
        if (shareActionProvider != null) {
            shareActionProvider.setShareIntent(shareIntent);
        }
    } */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_settings:
                  return true;
            case R.id.action_share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/*");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareContent.toString());
                startActivity(Intent.createChooser(shareIntent, "Share link using"));
        }

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        } */

        return super.onOptionsItemSelected(item);
    }
}
