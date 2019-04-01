package com.example.a533.cours5_Jp.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.a533.cours5_Jp.MainActivity;
import com.example.a533.cours5_Jp.R;
import com.example.a533.cours5_Jp.notification.model.ImportantMessageModel;
import com.example.a533.cours5_Jp.notification.model.MessageModel;

public class NotificationCreator {
    public static Notification createNotificationForMessage(Context context, MessageModel message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "42")
                .setSmallIcon(R.drawable.notification)
                .setContentTitle(message.getSender())
                .setContentText(message.getMessage())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        return builder.build();
    }

    public static Notification createNotificationForImportantMessage(Context context, ImportantMessageModel message) {
        Intent importantMessageIntent = new Intent(context, MainActivity.class);
        PendingIntent importantMessagePendingIntent = PendingIntent.getActivity(context, 0, importantMessageIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "43")
                .setSmallIcon(R.drawable.notification)
                .setContentTitle(message.getSender())
                .setContentText(message.getMessage())
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .addAction(R.drawable.notification, "Marqué comme lue", importantMessagePendingIntent);
        return builder.build();
    }
}
