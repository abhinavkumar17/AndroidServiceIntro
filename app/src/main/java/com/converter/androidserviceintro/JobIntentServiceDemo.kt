package com.converter.androidserviceintro

import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import androidx.core.app.JobIntentService


class JobIntentServiceDemo: JobIntentService() {

    private val TAG = "ExampleJobIntentService"
     companion object{
         fun enqueueWork(context: Context?, work: Intent?) {
                        context?.let { work?.let { it1 ->
                 enqueueWork(it, JobIntentServiceDemo::class.java, 123,
                     it1
                 )
             } }
         }
     }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

    override fun onHandleWork(intent: Intent) {
        Log.d(TAG, "onHandleWork")
        val input = intent.getStringExtra("inputExtra")
        for (i in 0..9) {
            Log.d(TAG, "$input - $i")
            if (isStopped) return
            SystemClock.sleep(1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onStopCurrentWork(): Boolean {
        Log.d(TAG, "onStopCurrentWork")
        return super.onStopCurrentWork()
    }
}