package com.example.tools.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.tools.R;

public class FlashLight extends AppCompatActivity {

    private boolean isFlashOn = false;
    private CameraManager cameraManager;
    private String cameraId;
    private ImageView btnToggleFlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_light);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);  // hide mobile key button
        // Set the status bar color
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        btnToggleFlash = findViewById(R.id.btnToggleFlash);
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        btnToggleFlash.setOnClickListener(view -> {
            if (isFlashOn) {
                turnOffFlashLight();
            } else {
                turnOnFlashLight();
            }
        });
    }

    private void turnOnFlashLight() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, true);
            }
            isFlashOn = true;
            btnToggleFlash.setImageResource(R.drawable.torch_on);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void turnOffFlashLight() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, false);
            }
            isFlashOn = false;
            btnToggleFlash.setImageResource(R.drawable.torch_off);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}
