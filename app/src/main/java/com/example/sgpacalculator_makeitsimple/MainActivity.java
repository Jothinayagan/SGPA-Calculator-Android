package com.example.sgpacalculator_makeitsimple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button = (Button) findViewById(R.id.mainLogin);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClick(View view){
        Intent i = new Intent(MainActivity.this, loginActivity.class);
        startActivity(i);
        finish();
    }
}
