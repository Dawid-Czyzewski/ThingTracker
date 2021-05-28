package com.example.thingstracker.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.View
import com.example.thingstracker.R
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun quit(view: View){
        exitProcess(-1)
    }

    fun goToAddNewThinkActivity(view:View){
        val intent = Intent(this,NewThinkActivity::class.java)
        startActivity(intent)
    }
}