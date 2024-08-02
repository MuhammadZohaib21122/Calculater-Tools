package com.example.tools.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tools.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AgeCalculator extends AppCompatActivity {

    private EditText etBirthDate;
    private Button btnCalculateAge;
    private TextView tvResultYears, tvResultMonths, tvResultDays,tvNextBirthday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calculator);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);  // hide mobile key button
        // Set the status bar color
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.aboutDivider));

        etBirthDate = findViewById(R.id.etBirthDate);
        btnCalculateAge = findViewById(R.id.btnCalculateAge);
        tvResultYears = findViewById(R.id.tvResultYears);
        tvResultMonths = findViewById(R.id.tvResultMonths);
        tvResultDays = findViewById(R.id.tvResultDays);
        tvNextBirthday = findViewById(R.id.tvNextBirthday);

        btnCalculateAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide the keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }

                String birthDateString = etBirthDate.getText().toString();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date birthDate = sdf.parse(birthDateString);
                    calculateAge(birthDate);
                    calculateNextBirthday(birthDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        etBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvNextBirthday.setVisibility(View.GONE);
                tvResultYears.setText("Years :");
                tvResultMonths.setText("Months :");
                tvResultDays.setText("Days :");
            }
        });
    }

    private void calculateAge(Date birthDate) {
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthDate);

        Calendar today = Calendar.getInstance();
        today.setTime(new Date());

        int years = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        int months = today.get(Calendar.MONTH) - birth.get(Calendar.MONTH);
        int days = today.get(Calendar.DAY_OF_MONTH) - birth.get(Calendar.DAY_OF_MONTH);

        if (days < 0) {
            months--;
            days += today.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if (months < 0) {
            years--;
            months += 12;
        }

        long differenceInMillis = today.getTimeInMillis() - birth.getTimeInMillis();
        long differenceInSeconds = TimeUnit.MILLISECONDS.toSeconds(differenceInMillis);

        tvResultYears.setText("Years:  "  + years);
        tvResultMonths.setText("Months:  " + months);
        tvResultDays.setText("Days:  " + days);
    }

    private void calculateNextBirthday(Date birthDate) {
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthDate);

        Calendar today = Calendar.getInstance();
        today.setTime(new Date());

        Calendar nextBirthday = Calendar.getInstance();
        nextBirthday.setTime(birthDate);
        nextBirthday.set(Calendar.YEAR, today.get(Calendar.YEAR));

        if (today.after(nextBirthday)) {
            nextBirthday.set(Calendar.YEAR, today.get(Calendar.YEAR) + 1);
        }

        int days = nextBirthday.get(Calendar.DAY_OF_MONTH) - today.get(Calendar.DAY_OF_MONTH);
        int months = nextBirthday.get(Calendar.MONTH) - today.get(Calendar.MONTH);

        if (days < 0) {
            months--;
            days += today.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if (months < 0) {
            months += 12;
        }

        tvNextBirthday.setText("Your Next Birthday is :  " + months + " months and " + days + " days");
        tvNextBirthday.setVisibility(View.VISIBLE);
    }
}