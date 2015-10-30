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

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;

import java.io.IOException;

public class StoryScreenActivity extends AppCompatActivity {


    private Button flashButton;
    private Weather weather;
    private String weatherUrl;
    private static final String TAG = StoryScreenActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_screen);
        flashButton = (Button) findViewById(R.id.flash_button);
        weather= new Weather();
        weatherUrl="http://api.openweathermap.org/data/2.5/weather?zip=%1$d,us&appid=bd82977b86bf27fb59a04b61b657fb6f";

        //Update the weather if network is available
        if(isNetworkAvailable())
            updateWeather(94102);

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
                    weather.update(jsonData,zipcode);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
}
