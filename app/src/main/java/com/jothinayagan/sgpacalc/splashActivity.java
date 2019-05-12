package com.jothinayagan.sgpacalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splashActivity extends AppCompatActivity {
    private static int splash_time_out = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent splash = new Intent(splashActivity.this , LoginActivity.class);
                startActivity(splash);
                finish();
            }
        },splash_time_out);
    }
}