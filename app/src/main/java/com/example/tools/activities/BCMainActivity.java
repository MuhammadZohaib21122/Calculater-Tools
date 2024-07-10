package com.example.tools.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tools.R;

public class BCMainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SCAN = 1;
    private TextView scannedLinkTextView;

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bcmain);

        Button scanButton = findViewById(R.id.scan_button);
        scannedLinkTextView = findViewById(R.id.scanned_link_textview);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BCMainActivity.this, ScannerActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String scannedLink = data.getStringExtra("scanned_link");
                if (scannedLink != null) {
                    scannedLinkTextView.setText(scannedLink);
                    Linkify.addLinks(scannedLinkTextView, Linkify.WEB_URLS);
                    scannedLinkTextView.setMovementMethod(LinkMovementMethod.getInstance());
                    scannedLinkTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openInChrome(scannedLink);
                        }
                    });
                }
            }
        }
    }

    private void openInChrome(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        browserIntent.setPackage("com.android.chrome");

        if (browserIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(browserIntent);
        } else {
            // Fallback to default browser
            browserIntent.setPackage(null);
            startActivity(browserIntent);
        }
    }
}
