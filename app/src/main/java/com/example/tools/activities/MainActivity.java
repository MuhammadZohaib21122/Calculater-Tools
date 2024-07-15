package com.example.tools.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tools.R;

public class MainActivity extends AppCompatActivity {
    public static Intent serviceIntent;
    TextView btnCalculater, btnStopWatch,btnQRScanner,btnBarCodeScanner,btnFlashLight,btnCalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);  // hide mobile key button
        // Set the status bar color
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        btnCalculater = findViewById(R.id.btnCalculater);
        btnStopWatch = findViewById(R.id.btnStopWatch);
        btnQRScanner = findViewById(R.id.btnQRScanner);
        btnBarCodeScanner = findViewById(R.id.btnBarCodeScanner);
        btnFlashLight = findViewById(R.id.btnFlashLight);
        btnCalender = findViewById(R.id.btnCalender);

        btnQRScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QRCodeScanner.class);
                startActivity(intent);
            }
        });
        btnCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalenderActivity.class);
                startActivity(intent);
            }
        });
        btnFlashLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FlashLight.class);
                startActivity(intent);
            }
        });
        btnCalculater.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this, Calculater.class);
                 startActivity(intent);
             }
         });
        btnStopWatch.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this, StopWatch.class);
                 startActivity(intent);
             }
         });
        btnBarCodeScanner.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this, BCMainActivity.class);
                 startActivity(intent);
             }
         });

    }

}