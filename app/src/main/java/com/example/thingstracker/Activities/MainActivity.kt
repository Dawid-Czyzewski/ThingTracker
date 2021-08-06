package com.example.thingstracker.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.Toast
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

    fun goToAddNewThingActivity(view: View){
        val intent = Intent(this, NewThingActivity::class.java)
        startActivity(intent)
    }

    fun goToThingsViewActivity(view: View){
        val intent = Intent(this, ThingsViewActivity::class.java)
        startActivity(intent)
    }
}