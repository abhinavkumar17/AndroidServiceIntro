package com.converter.androidserviceintro

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    private lateinit var editTextInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextInput = findViewById(R.id.edit_text_input)
    }

    fun startForegroundService(view: View) {
        val input = editTextInput.text.toString()
        val serviceIntent = Intent(this, ForegroundService::class.java)
        serviceIntent.putExtra("inputExtra", input)
        ContextCompat.startForegroundService(this, serviceIntent)
    }

    fun stopForegroundService(view: View) {
        val serviceIntent = Intent(this, ForegroundService::class.java)
        stopService(serviceIntent)
    }

    fun startIntentService(view: View) {
        val input = editTextInput.text.toString()
        val serviceIntent = Intent(this, IntentServiceDemo()::class.java)
        serviceIntent.putExtra("inputExtra", input)
        ContextCompat.startForegroundService(this, serviceIntent)
    }

    fun enqueueWork(view: View) {
        val input = editTextInput.text.toString()
        val serviceIntent = Intent(this, JobIntentServiceDemo::class.java)
        serviceIntent.putExtra("inputExtra", input)
        JobIntentServiceDemo.enqueueWork(this, serviceIntent)
    }

}
