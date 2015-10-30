package purpamine.com.cloudweather;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class StoryScreenActivity extends AppCompatActivity {


    private Button flashButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_screen);
        flashButton = (Button) findViewById(R.id.flash_button);


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
}
