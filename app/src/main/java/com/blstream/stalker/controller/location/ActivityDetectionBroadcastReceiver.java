package com.blstream.stalker.controller.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import com.blstream.stalker.R;
import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;

/**
 *
 */
public class ActivityDetectionBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "BroadcastReceiver: ";

    @Override
    public void onReceive(Context context, Intent intent) {
        ArrayList<DetectedActivity> detectedActivities = intent.getParcelableArrayListExtra(Constants.STRING_EXTRA);
        String activityString = "";
        for (DetectedActivity activity : detectedActivities) {
            activityString += "Activity: " + getDetectedActivity(activity.getType(), context)
                    + ", Confidence: " + activity.getConfidence() + "%\n";
        }
        Log.d(TAG, "onReceive: ");
        Toast.makeText(context, activityString, Toast.LENGTH_SHORT).show();
    }

    private String getDetectedActivity(int detectedActivityType, Context context) {
        Resources resources = context.getResources();
        switch (detectedActivityType) {
            case DetectedActivity.IN_VEHICLE:
                return resources.getString(R.string.in_vehicle);
            case DetectedActivity.ON_BICYCLE:
                return resources.getString(R.string.on_bicycle);
            case DetectedActivity.ON_FOOT:
                return resources.getString(R.string.on_foot);
            case DetectedActivity.RUNNING:
                return resources.getString(R.string.running);
            case DetectedActivity.WALKING:
                return resources.getString(R.string.walking);
            case DetectedActivity.STILL:
                return resources.getString(R.string.still);
            case DetectedActivity.TILTING:
                return resources.getString(R.string.tilting);
            case DetectedActivity.UNKNOWN:
                return resources.getString(R.string.unknown);
            default:
                return resources.getString(R.string.unidentifiable_activity, detectedActivityType);
        }
    }
}
