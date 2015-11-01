package purpamine.com.cloudweather;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;

public class StoryScreenActivity extends AppCompatActivity {


    private Button flashButton;
    private Weather weather;
    private String weatherUrl;
    private static final String TAG = StoryScreenActivity.class.getSimpleName();

    private TextView temperatureTextView;
    private TextView cityTextView;
    private TextView descriptionTextView;
    private EditText zipcodeEditText;
    private ImageView weatherIcon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_screen);
        flashButton = (Button) findViewById(R.id.flash_button);
        weather= new Weather();
        weatherUrl="http://api.openweathermap.org/data/2.5/weather?zip=%1$d,us&appid=bd82977b86bf27fb59a04b61b657fb6f";

        //Instantiate views
        temperatureTextView= (TextView) findViewById(R.id.temperature);
        cityTextView = (TextView) findViewById(R.id.city);
        descriptionTextView = (TextView) findViewById(R.id.description);
        zipcodeEditText = (EditText) findViewById(R.id.zipcode);
        weatherIcon = (ImageView) findViewById(R.id.weather_icon);

        //Update the weather if network is available
        if(isNetworkAvailable())
            updateWeather(94128);



        /*************************************************************
         *                    Button Effect on Touch
         *************************************************************/
        flashButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                    flashButton.setBackgroundColor(0xffff5f00);
                } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                    flashButton.setBackgroundColor(0xffcb3d00);
                }
                return false;
            }
        });

        /*************************************************************
         *                    Button On Click
         *************************************************************/
        flashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    updateWeather(Integer.parseInt(zipcodeEditText.getText().toString()));
                }
                catch (NumberFormatException e)
                {
                    Toast.makeText(getApplicationContext(), "Zipcode Must Be Digits!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /*************************************************************
     *             Update Weather Using OkHttp Client
     *************************************************************/
    public void updateWeather(final int zipcode)
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().
                              url(String.format(weatherUrl, zipcode)).
                              build();
        Call call = client.newCall(request);
        // Perform Asynchronous Web Service Call
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.v(TAG, "Something went wrong with the api request..");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String jsonData = response.body().string();
                try {
                    weather.update(jsonData, zipcode);
                    updatePhoton();
                    // update display on main thread after completing network calls and parse
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateDisplay();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Oops, Check Your Zipcode!!", Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });

    }

    /*************************************************************
     *             Update Photon Using OkHttp Client
     *************************************************************/
    public void updatePhoton()
    {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder()
                .add("access_token", "073ac659f61a49f0cac9308c0207896b2e245407")
                .add("args", String.format("%1$s:%2$d:%3$d:%4$s",
                        weather.getCity(),
                        weather.getZipcode(),
                        weather.getTemperature(),
                        weather.getDescription()))
                .build();

        Request request = new Request.Builder().
                url("https://api.particle.io/v1/devices/AKA/weather").
                post(formBody).
                build();
        Call call = client.newCall(request);
        // Perform Asynchronous Web Service Call
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.v(TAG, "Something went wrong with the api request..");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String jsonData = response.body().string();
                Log.v(TAG,jsonData);
            }
        });

    }

    /*************************************************************
     *             Check if network is available
     *************************************************************/
    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    private void updateDisplay()
    {
        temperatureTextView.setText(weather.getTemperature()+"");
        cityTextView.setText(weather.getCity().toUpperCase());
        descriptionTextView.setText(weather.getDescription().toUpperCase());
        zipcodeEditText.setText(weather.getZipcode() + "");

        weatherIcon.setImageResource(R.drawable.mist);

    }
}
