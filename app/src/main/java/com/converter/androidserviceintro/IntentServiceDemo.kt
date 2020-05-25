package com.converter.androidserviceintro

import android.app.IntentService
import android.app.Notification
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.PowerManager
import android.os.PowerManager.WakeLock
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import com.converter.androidserviceintro.App.channel.CHANNEL_ID


class IntentServiceDemo: IntentService("") {

    private val TAG = "ExampleIntentService"
    private var wakeLock: WakeLock? = null

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
        val powerManager =
            getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = powerManager.newWakeLock(
            PowerManager.PARTIAL_WAKE_LOCK,
            "ExampleApp:Wakelock"
        )
        wakeLock?.acquire()
        Log.d(TAG, "Wakelock acquired")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Example IntentService")
                .setContentText("Running...")
                .build()
            startForeground(1, notification)
        }
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent")
        val input: String = intent!!.getStringExtra("inputExtra")
        for (i in 0..9) {
            Log.d(TAG, "$input - $i")
            SystemClock.sleep(1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy");
        wakeLock?.release();
        Log.d(TAG, "Wakelock released");
    }
}