package com.example.android.preview.support.wearable.notifications;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;

/**
 * Created by Michael Yoon Huh on 6/14/2014.
 */
public class TrainNotifier extends Activity {

    TrainData trainRoute; // Train route object.

    private Handler mHandler;
    private Spinner mPresetSpinner;
    private Spinner mPrioritySpinner;
    private Spinner mActionsSpinner;
    private CheckBox mIncludeLargeIconCheckbox;
    private CheckBox mLocalOnlyCheckbox;
    private CheckBox mIncludeContentIntentCheckbox;
    private CheckBox mIncludeContentIntentRequiredCheckbox;

    // ACTIVITY FUNCTIONALITY

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // NEW CODE
        trainRoute = new TrainData();
        trainRoute.initialize(); // Initialize the trainRoute code.

        setUpNotification();
    }

    private void setUpNotification() {

        // Build intent for notification content
        Intent intent = new Intent(this, TrainNotifier.class);

        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, intent, 0);

        // Specify the 'big view' content to display the long
        // event description that may not fit the normal content text.
        //NotificationCompat.BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
        //bigStyle.bigText(trainRoute.getStationName() + "\n" + trainRoute.getTrainLine() + "\n" + trainRoute.gettrainArrivalTime());

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setContentTitle("TRAIN ARRIVING")
                        .setContentText("Test")
                                //.setContentText(trainRoute.getStationName() + "\n\n" + trainRoute.getTrainLine() + "\n\n" + trainRoute.gettrainArrivalTime() + " minutes")
                        .setContentIntent(viewPendingIntent);
        //.setStyle(bigStyle);

        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);


        // Build the notification and issues it with notification manager.
        //        notificationManager.notify(001, notificationBuilder.build());

        postNotifications();
    }

    /**
     * Begin to re-post the sample notification(s).
     */
    private void updateNotifications() {
        // Disable messages to skip notification deleted messages during cancel.
        sendBroadcast(new Intent(NotificationIntentReceiver.ACTION_DISABLE_MESSAGES)
                .setClass(this, NotificationIntentReceiver.class));

        // Cancel all existing notifications to trigger fresh-posting behavior: For example,
        // switching from HIGH to LOW priority does not cause a reordering in Notification Shade.
        NotificationManagerCompat.from(this).cancelAll();

        // Post the updated notifications on a delay to avoid a cancel+post race condition
        // with notification manager.
        //mHandler.removeMessages(MSG_POST_NOTIFICATIONS);
        //mHandler.sendEmptyMessageDelayed(MSG_POST_NOTIFICATIONS, POST_NOTIFICATIONS_DELAY_MS);
    }

    /**
     * Post the sample notification(s) using current options.
     */
    private void postNotifications() {
        sendBroadcast(new Intent(NotificationIntentReceiver.ACTION_ENABLE_MESSAGES)
                .setClass(this, NotificationIntentReceiver.class));

        NotificationPreset preset = NotificationPresets.PRESETS[
                mPresetSpinner.getSelectedItemPosition()];
        PriorityPreset priorityPreset = PriorityPresets.PRESETS[
                mPrioritySpinner.getSelectedItemPosition()];
        ActionsPreset actionsPreset = ActionsPresets.PRESETS[
                mActionsSpinner.getSelectedItemPosition()];
        mIncludeContentIntentCheckbox.setVisibility(preset.contentIntentRequired() ?
                View.GONE : View.VISIBLE);
        mIncludeContentIntentRequiredCheckbox.setVisibility(preset.contentIntentRequired() ?
                View.VISIBLE : View.GONE);
        NotificationPreset.BuildOptions options = new NotificationPreset.BuildOptions(
                priorityPreset,
                actionsPreset,
                mIncludeLargeIconCheckbox.isChecked(),
                mLocalOnlyCheckbox.isChecked(),
                mIncludeContentIntentCheckbox.isChecked());
        Notification[] notifications = preset.buildNotifications(this, options);

        // Post new notifications
        for (int i = 0; i < notifications.length; i++) {
            NotificationManagerCompat.from(this).notify(i, notifications[i]);
        }
    }
}
