package com.slack.csci3308project.dailyfortune;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;

/**
 * Class for receiving alarms from Android's AlarmManager.
 * These alarms are set for displaying a quote when the user-set alarm is triggered.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Button generalButton = (Button) ((Activity) context).findViewById(R.id.button);
        generalButton.performClick();
        ((MainActivity) context).searchAlarms();
    }
}