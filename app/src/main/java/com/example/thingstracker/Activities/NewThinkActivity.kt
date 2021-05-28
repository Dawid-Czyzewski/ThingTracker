package com.example.thingstracker.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.thingstracker.R

class NewThinkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_think)
    }


    fun backToMenu(view: View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}