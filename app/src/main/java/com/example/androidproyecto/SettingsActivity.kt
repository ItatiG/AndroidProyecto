package com.example.androidproyecto

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class SettingsActivity : AppCompatActivity()
{
    private var mNotificationManager: NotificationManager? = null
    private val NOTIFICATION_ID: Int = 0
    private val PRIMARY_CHANNEL_ID = "primary_notification_channel"

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        /*supportFragmentManager
            .beginTransaction()
            .replace(R.id-sett, SettingsFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)*/
        val alarmToggle: ToggleButton = findViewById(R.id.alarmToggle)
        mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notifyIntent = Intent(this, AlarmReceiver::class.java)

        val alarmUp = PendingIntent.getBroadcast(
            this,
            NOTIFICATION_ID,
            notifyIntent,
            PendingIntent.FLAG_NO_CREATE
        ) != null

        alarmToggle.isChecked = alarmUp

        val notifyPendingIntent = PendingIntent.getBroadcast(
            this,
            NOTIFICATION_ID,
            notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmToggle.setOnCheckedChangeListener { compoundButton, isChecked ->
            val toastMessage: String
            toastMessage = if (isChecked)
            {
                val repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES
                val triggerTime = (SystemClock.elapsedRealtime() + repeatInterval)

                alarmManager.setInexactRepeating(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    triggerTime,
                    repeatInterval,
                    notifyPendingIntent
                )
            "Alarma activada"
            }
            else
            {
                mNotificationManager?.cancelAll()
                alarmManager.cancel(notifyPendingIntent)
                "Alarma desactivada"
            }
            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT)
                .show()
        }
        createNotificationChannel()
    }

    class SettingsFragment : PreferenceFragmentCompat()
    {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?)
        {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }

    fun createNotificationChannel()
    {
        mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID,
                "Notificación",
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Notifica cada 15 minutos"
            mNotificationManager!!.createNotificationChannel(notificationChannel)
        }
    }
}