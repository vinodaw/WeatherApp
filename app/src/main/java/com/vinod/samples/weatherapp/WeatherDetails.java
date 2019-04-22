package com.vinod.samples.weatherapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WeatherDetails extends AppCompatActivity {
    private TextView temp1;
    private TextView minmax;
    private TextView weathercond;
    private ShareActionProvider shareActionProvider;
    private StringBuilder shareContent = new StringBuilder("");
    private static final String TAG = "WeatherDetails";

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
    protected void onStart() {
        super.onStart();

    }

    private void takeScreenshot(View view){

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),
                view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        //File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"myimages");

        Log.d(TAG, "takeScreenshot: ");

        OutputStream out = null;
        File imageFile = new File(imageFolder,"weatherscr.jpg");

        Log.d(TAG, "takeScreenshot: "+imageFile.getAbsolutePath());
        try {
            imageFolder.mkdirs();
            //new File(path).mkdir();
            out = new FileOutputStream(imageFile);
            // choose JPEG format
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);

            out.flush();
            Log.d(TAG, "takeScreenshot: successful");

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/jpeg");
            shareIntent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(this,"com.vinod.samples.weatherapp.fileprovider",imageFile));
            startActivity(Intent.createChooser(shareIntent, "Share link using"));
        } catch (FileNotFoundException e) {
            // manage exception ...
            Log.d(TAG, "takeScreenshot: FileNotFoundException"+e.getMessage());
        } catch (IOException e) {
            // manage exception ...
            Log.d(TAG, "takeScreenshot: IOException "+e.getMessage());
        } finally {

            try {
                if (out != null) {
                    out.close();
                }

            } catch (Exception exc) {
                Log.d(TAG, "takeScreenshot: Exception "+exc.getMessage());
            }

        }
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

                ViewGroup rootView = (ViewGroup) ((ViewGroup) this
                        .findViewById(android.R.id.content)).getChildAt(0);

                takeScreenshot(rootView);

        }

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        } */

        return super.onOptionsItemSelected(item);
    }
}
