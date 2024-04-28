package com.example.tools.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tools.R;

public class PoundToPkrActivity extends AppCompatActivity {

    float poundPrice = 349.50f;

    Button btnConvertToPkr;

    EditText etEnterPound;
    TextView tvPkrIs;

    TextWatcher textWatcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pound_to_pkr);
        tvPkrIs = findViewById(R.id.tvPkrIs);
        btnConvertToPkr = findViewById(R.id.btnConvertToPkr);
        etEnterPound = findViewById(R.id.etEnterPound);

        btnConvertToPkr.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                String text = etEnterPound.getText().toString().trim();

                if (!text.isEmpty()) {
                    try {

                        float dollarValue = Float.parseFloat(etEnterPound.getText().toString().trim());
                        if ( dollarValue >= 0 )
                        {
                            tvPkrIs.setText(Float.toString(poundPrice * dollarValue));
                        }

                    } catch (NumberFormatException e) {

                        etEnterPound.setError("Invalid number");
                    }
                } else {
                    etEnterPound.setError("Please enter a number");
                }


            }

        });

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!etEnterPound.getText().toString().isEmpty())
                {
                    float dollarValue = Float.parseFloat(etEnterPound.getText().toString().trim());
                    if ( dollarValue >= 0 )
                    {
                        tvPkrIs.setText("PKR: "+Float.toString(poundPrice * dollarValue));
                    }
                }
                else
                {
                    tvPkrIs.setText("0");
                }

            }
        };

        etEnterPound.addTextChangedListener(textWatcher);

    }
}