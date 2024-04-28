package com.example.tools.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tools.R;

public class MainActivity extends AppCompatActivity {
    Button btnShowMyName, btnShowMyAge, btnCalculator, btnDollarToPkr, btnPoundToPkr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowMyName = findViewById(R.id.btnShowMyName);
        btnShowMyAge = findViewById(R.id.btnShowMyAge);
        btnCalculator = findViewById(R.id.btnCalculator);
        btnDollarToPkr = findViewById(R.id.btnDollarToPkr);
        btnPoundToPkr = findViewById(R.id.btnPoundToPkr);

        btnShowMyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowMyNameActivity.class);
                startActivity(intent);
            }
        });
        btnShowMyAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowMyAgeActivity.class);
                startActivity(intent);
            }
        });
        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalculatorAActivity.class);
                startActivity(intent);
            }
        });

        btnDollarToPkr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DollarToPKrActivity.class );
                startActivity(intent);

            }
        });
        btnPoundToPkr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PoundToPkrActivity.class );
                startActivity(intent);

            }
        });

    }

}