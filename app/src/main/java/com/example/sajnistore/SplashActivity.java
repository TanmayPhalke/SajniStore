package com.example.sajnistore;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_DURATION = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Set a timer to delay the start of the MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create an intent that starts the MainActivity
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);

                // Close this activity
                finish();
            }
        }, SPLASH_DISPLAY_DURATION);
    }
}
