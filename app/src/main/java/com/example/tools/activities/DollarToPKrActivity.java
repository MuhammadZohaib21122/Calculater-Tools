package com.example.tools.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tools.R;

public class DollarToPKrActivity extends AppCompatActivity {

    float dollarPrice = 279.78f;

    Button btnConvertToPkr;

    EditText etEnterDollar;
    TextView tvPkrIs;

    TextWatcher textWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dollar_to_pkr);

        tvPkrIs = findViewById(R.id.tvPkrIs);
        btnConvertToPkr = findViewById(R.id.btnConvertToPkr);
        etEnterDollar = findViewById(R.id.etEnterDollar);

        btnConvertToPkr.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                String text = etEnterDollar.getText().toString().trim();

                if (!text.isEmpty()) {
                    try {

                        float dollarValue = Float.parseFloat(etEnterDollar.getText().toString().trim());
                        if ( dollarValue >= 0 )
                        {
                            tvPkrIs.setText(Float.toString(dollarPrice * dollarValue));
                        }

                    } catch (NumberFormatException e) {

                        etEnterDollar.setError("Invalid number");
                    }
                } else {
                    etEnterDollar.setError("Please enter a number");
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

                if (!etEnterDollar.getText().toString().isEmpty())
                {
                    float dollarValue = Float.parseFloat(etEnterDollar.getText().toString().trim());
                    if ( dollarValue >= 0 )
                    {
                        tvPkrIs.setText("PKR: "+Float.toString(dollarPrice * dollarValue));
                    }
                }
                else
                {
                    tvPkrIs.setText("0");
                }

            }
        };

        etEnterDollar.addTextChangedListener(textWatcher);

    }

}