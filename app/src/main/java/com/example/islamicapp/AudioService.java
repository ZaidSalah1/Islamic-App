package com.example.islamicapp;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.islamicapp.Activties.NasheedsActivity;
import com.example.islamicapp.R;

public class AudioService extends Service {

    public static final String CHANNEL_ID = "AudioPlaybackChannel";
    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // In AudioService
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int audioResId = intent.getIntExtra("audioResId", -1);

        if (audioResId != -1) {
            playAudio(audioResId);

            // Create a notification and start the service in the foreground
            Notification notification = createNotification();
            startForeground(1, notification);  // 1 is the notification ID
        }

        return START_STICKY;
    }

    private void playAudio(int audioResId) {
        // Release any previously initialized media player
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        // Initialize a new media player with the selected audio resource
        mediaPlayer = MediaPlayer.create(this, audioResId);

        // Set up an OnCompletionListener to handle when the audio finishes
        mediaPlayer.setOnCompletionListener(mp -> {
            // Stop the foreground service when the audio is done playing
            stopForeground(true);  // Stop the foreground notification
            stopSelf();  // Stop the service
        });

        // Start playing the audio
        mediaPlayer.start();
    }


    private Notification createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.food_athkar)
                .setContentTitle("Playing Audio")
                .setContentText("Your audio is playing.")
                .setPriority(NotificationCompat.PRIORITY_LOW);  // Avoid interrupting the user unnecessarily

        return builder.build();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    private void startForegroundAudio(int audioResId) {
        // Create and start MediaPlayer
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, audioResId);
        mediaPlayer.setLooping(false); // Set to true if you want looping
        mediaPlayer.start();

        // Create a notification for the foreground service
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, NasheedsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Playing Audio")
                .setContentText("Your audio is playing in the background.")
                .setSmallIcon(R.drawable.quran_icon_new) // Replace with your own icon
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Audio Playback Channel",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(serviceChannel);
            }
        }
    }
}
