package com.rivanov.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class NotificationActivity : AppCompatActivity() {
    val CHANNEL_ID = "channel_id"
    val CHANNEL_NAME = "channel_name"
    val NOTIFICATION_ID = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        createNotificationChannel()

        val remindMeButton: Button = findViewById(R.id.remind_me_button);
        val secondsEditText: EditText = findViewById(R.id.seconds_input)

        val notification = NotificationCompat.Builder(
            this,
            CHANNEL_ID
        )
            .setContentTitle("Time is up!")
            .setContentText("Do the whatever you were lazy to do")
            .setSmallIcon(androidx.constraintlayout.widget.R.drawable.abc_btn_radio_material)
            .setColor(Color.MAGENTA)
            .build()

        val notManager = NotificationManagerCompat.from(this)

        remindMeButton.setOnClickListener {

            val seconds: Long = Integer.parseInt(secondsEditText.text.toString()).toLong()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                val notification = NotificationCompat.Builder(
                    this,
                    CHANNEL_ID
                )
                    .setContentTitle("Time is up!")
                    .setContentText("Do the whatever you were too lazy to do $seconds seconds ago!")
                    .setSmallIcon(androidx.constraintlayout.widget.R.drawable.abc_btn_radio_material)
                    .setColor(Color.MAGENTA)
                    .build()

                Executors.newSingleThreadScheduledExecutor().schedule(
                    {
                        notManager.notify(NOTIFICATION_ID, notification)
                    },
                    seconds,
                    TimeUnit.SECONDS
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val notificationChannel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
            .apply {
                lightColor = Color.YELLOW
                enableLights(true)
            }

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        manager.createNotificationChannel(notificationChannel)
    }
}

