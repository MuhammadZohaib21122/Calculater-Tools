package com.example.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.tools.activities.PrefUtil;
import com.example.tools.activities.TimerService;

import java.util.Objects;

public class StopwatchNotificationActionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (Objects.requireNonNull(intent.getStringExtra("action"))){
            case "p":
                Log.v("actionClicked", "p");

                TimerService.timerHandler.removeCallbacks(TimerService.timerRunnable);
                PrefUtil.setIsRunningInBackground(context, false);
                PrefUtil.setWasTimerRunning(context, false);
                break;
            case "s":
                Log.v("actionClicked", "s");

                TimerService.timerHandler.removeCallbacks(TimerService.timerRunnable);
                TimerService.timerHandler.postDelayed(TimerService.timerRunnable, 1000);
                PrefUtil.setIsRunningInBackground(context, true);
                PrefUtil.setWasTimerRunning(context, true);
                break;
        }

    }
}
