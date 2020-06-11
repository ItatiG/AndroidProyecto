package com.example.androidproyecto

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

//3.1 Crear el receptor de difusión
class AlarmReceiver : BroadcastReceiver()
{
    //2.1.1
    private var mNotificationManager: NotificationManager? = null
    //2.1.3
    private val NOTIFICATION_ID: Int = 0
    private val PRIMARY_CHANNEL_ID = "primary_notification_channel"

    override fun onReceive(context: Context?, intent: Intent?)
    {
        mNotificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        deliverNotification(context)
    }

    //Establecer el contenido de la notificación Intent
    //2.3.1
    private fun deliverNotification(context: Context?)
    {
        //2.3.2
        val contentIntent = Intent(context, MainActivity::class.java)
        //2.3.3
        val contentPendingIntent = PendingIntent.getActivity(
            context,
            NOTIFICATION_ID,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        //2.4.2
        val builder = NotificationCompat.Builder(context!!, PRIMARY_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_stand_up)
            .setContentTitle("Alerta")
            .setContentText("Bienvenido a esta a aplicación.")
            .setContentIntent(contentPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)

        //Entregue la notificación y pruebe su aplicación
        //2.5.1
        mNotificationManager?.notify(NOTIFICATION_ID, builder.build())
    }
}