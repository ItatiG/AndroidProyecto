package com.example.androidproyecto.background

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.example.androidproyecto.MainActivity
import com.example.androidproyecto.NavigationActivity
import com.example.androidproyecto.R

class MusicaService : Service()
{
    var mediaPlayer: MediaPlayer? = null

    //Se inicia el servicio
    override fun onCreate()
    {
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.piano)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        Log.e("TAG", "onStartCommand")

        //Validación para comprobar la versión
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            //Ejecutar un servicio en primer plano
            val pendingIntent: PendingIntent =
                Intent(this, MainActivity::class.java).let { notificationIntent ->
                    PendingIntent.getActivity(this, 0, notificationIntent, 0)
                }

            val notification: Notification =
                //Notification.Builder(this, CHANNEL_DEFAULT_IMPORTANCE)
                Notification.Builder(this, NavigationActivity.CHANNEL_ID)
                    .setContentTitle("Música")//(getText(R.string.notification_title))
                    .setContentText("Servicio en primer plano")//(getText(R.string.notification_message))
                    .setSmallIcon(R.drawable.ic_menu_gallery)//(R.drawable.icon)
                    .setContentIntent(pendingIntent)
                    .setTicker("ticker")//(getText(R.string.ticker_text))
                    .build()
            //startForeground(ONGOING_NOTIFICATION_ID, notification)
            startForeground(1, notification)
        }
        //Hasta aquí

        mediaPlayer?.start()

        return Service.START_NOT_STICKY
    }

    override fun onDestroy()
    {
        Log.e("TAG", "onDestroy")

        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onBind(intent: Intent?): IBinder?
    {
        return null
    }
}