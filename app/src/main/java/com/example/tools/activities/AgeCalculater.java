package com.example.tools.activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tools.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AgeCalculater extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewNextBirthdayMonths, textViewNextBirthdayDays,
            textViewFinalYears, textViewFinalMonths, textViewFinalDays,
            textViewCurrentDay, textViewCalculate, textViewClear;

    private ImageView imageViewCalendarFirst, imageViewCalendarSecond;
    private EditText editTextBirthDay, editTextBirthMonth, editTextBirthYear,
            editTextCurrentDay, editTextCurrentMonth, editTextCurrentYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calculater);

        initializeViews();
        setupCurrentDate();
        setOnClickListeners();
    }

    private void initializeViews() {
        textViewNextBirthdayMonths = findViewById(R.id.textViewNextBirthdayMonths);
        textViewNextBirthdayDays = findViewById(R.id.textViewNextBirthdayDays);
        textViewFinalYears = findViewById(R.id.textViewFinalYears);
        textViewFinalMonths = findViewById(R.id.textViewFinalMonths);
        textViewFinalDays = findViewById(R.id.textViewFinalDays);
        textViewCurrentDay = findViewById(R.id.textViewCurrentDay);
        textViewCalculate = findViewById(R.id.textViewCalculate);
        textViewClear = findViewById(R.id.textViewClear);
        imageViewCalendarFirst = findViewById(R.id.imageViewCalenderFirst);
        imageViewCalendarSecond = findViewById(R.id.imageViewCalenderSecond);
        editTextBirthDay = findViewById(R.id.editTextBirthDay);
        editTextBirthMonth = findViewById(R.id.editTextBirthMonth);
        editTextBirthYear = findViewById(R.id.editTextBirthYear);
        editTextCurrentDay = findViewById(R.id.editTextCurrentDay);
        editTextCurrentMonth = findViewById(R.id.editTextCurrentMonth);
        editTextCurrentYear = findViewById(R.id.editTextCurrentYear);
    }

    private void setupCurrentDate() {
        final Calendar c = Calendar.getInstance();
        editTextCurrentYear.setText(String.valueOf(c.get(Calendar.YEAR)));
        editTextCurrentMonth.setText(addZero(c.get(Calendar.MONTH) + 1));
        editTextCurrentDay.setText(addZero(c.get(Calendar.DAY_OF_MONTH)));

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        Date date = new Date(c.getTimeInMillis());
        String dayOfWeek = simpleDateFormat.format(date);
        textViewCurrentDay.setText(dayOfWeek);
        textViewCurrentDay.setVisibility(View.VISIBLE);
    }

    private String addZero(int number) {
        return (number < 10) ? "0" + number : String.valueOf(number);
    }

    private void setOnClickListeners() {
        textViewCalculate.setOnClickListener(this);
        textViewClear.setOnClickListener(this);
        imageViewCalendarSecond.setOnClickListener(this);
        imageViewCalendarFirst.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.imageViewCalenderSecond) {
            showDatePickerDialog();
        } else if (id == R.id.textViewCalculate) {
            calculateAndDisplay();
        } else if (id == R.id.textViewClear) {
            clearFields();
        }
    }

    private void showDatePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    editTextBirthDay.setText(addZero(dayOfMonth));
                    editTextBirthMonth.setText(addZero(monthOfYear + 1));
                    editTextBirthYear.setText(String.valueOf(year));
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void calculateAndDisplay() {
        if (TextUtils.isEmpty(editTextBirthDay.getText()) ||
                TextUtils.isEmpty(editTextBirthMonth.getText()) ||
                TextUtils.isEmpty(editTextBirthYear.getText())) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        calculateAge();
        nextBirthday();
    }

    private void clearFields() {
        editTextBirthDay.setText("");
        editTextBirthMonth.setText("");
        editTextBirthYear.setText("");
        Toast.makeText(this, "Successfully reset", Toast.LENGTH_SHORT).show();
    }

    private void nextBirthday() {
        int currentDay = Integer.parseInt(editTextCurrentDay.getText().toString());
        int currentMonth = Integer.parseInt(editTextCurrentMonth.getText().toString());
        int currentYear = Integer.parseInt(editTextCurrentYear.getText().toString());

        Calendar current = Calendar.getInstance();
        current.set(currentYear, currentMonth - 1, currentDay);

        int birthDay = Integer.parseInt(editTextBirthDay.getText().toString());
        int birthMonth = Integer.parseInt(editTextBirthMonth.getText().toString());
        int birthYear = Integer.parseInt(editTextBirthYear.getText().toString());

        Calendar birthday = Calendar.getInstance();
        birthday.set(birthYear, birthMonth - 1, birthDay);

        long difference = birthday.getTimeInMillis() - current.getTimeInMillis();

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(difference);

        textViewNextBirthdayMonths.setText(String.valueOf(cal.get(Calendar.MONTH)));
        textViewNextBirthdayDays.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
    }

    private void calculateAge() {
        int currentDay = Integer.parseInt(editTextCurrentDay.getText().toString());
        int currentMonth = Integer.parseInt(editTextCurrentMonth.getText().toString());
        int currentYear = Integer.parseInt(editTextCurrentYear.getText().toString());

        Calendar current = Calendar.getInstance();
        current.set(currentYear, currentMonth - 1, currentDay);

        int birthDay = Integer.parseInt(editTextBirthDay.getText().toString());
        int birthMonth = Integer.parseInt(editTextBirthMonth.getText().toString());
        int birthYear = Integer.parseInt(editTextBirthYear.getText().toString());

        Calendar birthday = Calendar.getInstance();
        birthday.set(birthYear, birthMonth - 1, birthDay);

        if (birthday.after(current)) {
            Toast.makeText(this, "Birthday can't be in the future", Toast.LENGTH_SHORT).show();
            return;
        }

        int calculatedDate = currentDay - birthDay;
        int calculatedMonth = currentMonth - birthMonth;
        int calculatedYear = currentYear - birthYear;

        if (calculatedMonth < 0) {
            calculatedYear--;
            calculatedMonth += 12;
        }

        if (calculatedDate < 0) {
            Calendar temp = (Calendar) current.clone();
            temp.add(Calendar.MONTH, -1);
            calculatedDate += temp.getActualMaximum(Calendar.DAY_OF_MONTH);
            calculatedMonth--;
        }

        textViewFinalDays.setText(String.valueOf(calculatedDate));
        textViewFinalMonths.setText(String.valueOf(calculatedMonth));
        textViewFinalYears.setText(String.valueOf(calculatedYear));
    }
}
