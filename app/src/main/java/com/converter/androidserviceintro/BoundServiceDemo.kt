package com.converter.androidserviceintro

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.*

class BoundServiceDemo: Service() {

    /*private val TAG = "BoundServiceDemo"

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
    }*/

    val UPPER_BOUND = 100

    // Binder given to clients
    private val mBinder: IBinder = LocalBinder()

    // Random number generator
    private val mGenerator: Random = Random()

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    // Method for clients
    fun getRandomNumber(): Int {
        return mGenerator.nextInt(UPPER_BOUND)
    }

    // Method for clients
    fun getNumber(num: Int): Int {
        return num
    }

    class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods
        val service: LocalService
            get() =// Return this instance of LocalService so clients can call public methods
                this@LocalService
    }
}