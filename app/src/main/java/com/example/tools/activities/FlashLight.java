package com.example.tools.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tools.R;

public class FlashLight extends AppCompatActivity {

    private boolean isFlashOn = false;
    private CameraManager cameraManager;
    private String cameraId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_light);

        ImageView btnToggleFlash = findViewById(R.id.btnToggleFlash);
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
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}