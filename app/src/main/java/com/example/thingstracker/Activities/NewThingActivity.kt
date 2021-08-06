package com.example.thingstracker.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.thingstracker.DataBaseManager
import com.example.thingstracker.R
import com.example.thingstracker.Thing

class NewThingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_thing)

    }


    fun backToMenu(view: View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun addNewThing(view: View){
        val _name: EditText = findViewById<EditText>(R.id._Name)
        val _place: EditText = findViewById<EditText>(R.id._Where)
        val db: DataBaseManager = DataBaseManager(this)

        val name = _name.text.toString()
        val place = _place.text.toString()

        if(!name.isEmpty() && !place.isEmpty() ){
            val status = db.addThing(Thing(0,name,place,null))
            if(status > -1){
                Toast.makeText(applicationContext,"added new Thing",Toast.LENGTH_LONG).show()
                _name.text.clear()
                _place.text.clear()
            }
        }else{
            Toast.makeText(applicationContext,"Name or Place cannot be blank!",Toast.LENGTH_LONG).show()
        }
    }
}