package com.example.tools.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tools.R;

public class CalculatorAActivity extends AppCompatActivity {

    Button btnADD, btnMultiply, btnSubtract, btnDivide, btnPercentage;
    EditText etNumber1, etNumber2;
    float num1, num2;
    TextView tvResultis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);


        btnADD = findViewById(R.id.btnAdd);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnPercentage=findViewById(R.id.btnPercentage);
        btnDivide = findViewById(R.id.btnDivide);
        etNumber1 = findViewById(R.id.etNumber1);
        etNumber2 = findViewById(R.id.etNumber2);
        tvResultis = findViewById(R.id.tvResultis);


        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfBothNumbersAreEntered();

                float res = num1 + num2;
                tvResultis.setText(getResources().getString(R.string.resultis) + res);

            }
        }); btnPercentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfBothNumbersAreEntered();

                float res = num1 % num2;
                tvResultis.setText(getResources().getString(R.string.resultis) + res);

            }
        });
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                checkIfBothNumbersAreEntered();

                float res = num1 * num2;
                tvResultis.setText(getResources().getString(R.string.resultis) + res);

            }
        });
        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                checkIfBothNumbersAreEntered();

                float res = num1 - num2;
                tvResultis.setText(getResources().getString(R.string.resultis) + res);

            }
        });
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                checkIfBothNumbersAreEntered();

                if (num2 == 0) {
                    Toast.makeText(CalculatorAActivity.this, "can not divide by 0", Toast.LENGTH_SHORT).show();
                }else{
                    float res = num1 / num2;
                    tvResultis.setText(getResources().getString(R.string.resultis) + res);

                }



            }
        });

    }


    private void checkIfBothNumbersAreEntered(){
        String text = etNumber1.getText().toString();
        if (text.trim().length() > 0) {

            num1 =  Float.parseFloat(etNumber1.getText().toString());

        } else {

            etNumber1.setError("please enter number");
        }
        String text2 = etNumber2.getText().toString();
        if (text2.trim().length() > 0) {

            num2 = Float.parseFloat(etNumber2.getText().toString());
        } else {

            etNumber2.setError("please enter number");
        }
    }

}