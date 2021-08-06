package com.example.thingstracker.Activities

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thingstracker.DataBaseManager
import com.example.thingstracker.R
import com.example.thingstracker.Thing
import com.example.thingstracker.ThingAdapter

class ThingsViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_things_view)

        reloadList()
    }

    private fun generateList(size: Int): List<Thing>{
        val list = ArrayList<Thing>()

        val db: DataBaseManager = DataBaseManager(this)
        val list2 = db.viewThings()

        for(i in 0 until size){
            val thing2 = list2[i]

            val thing = Thing(thing2.getId(),thing2.getName(),thing2.getPlace(),thing2.getDate())
            list += thing
        }

        return list
    }

    private fun thingCount(): Int{
        val db: DataBaseManager = DataBaseManager(this)

        val list = db.viewThings()

        return list.size
    }

     fun editThing(thing: Thing){
        val db: DataBaseManager = DataBaseManager(this)

        val updateDialog = Dialog(this,R.style.Theme_Dialog)
        updateDialog.setCancelable(false)
        updateDialog.setContentView(R.layout.dialog_layout)

        val name = updateDialog.findViewById<EditText>(R.id._Name2)
        val place = updateDialog.findViewById<EditText>(R.id._Where1)
        val button = updateDialog.findViewById<Button>(R.id.button6)
        name.setText(thing.getName())
        place.setText(thing.getPlace())

        button.setOnClickListener(View.OnClickListener {
            val _name = name.text.toString()
            val _place = place.text.toString()

            if(!_name.isEmpty() && !_place.isEmpty()){
                val status = db.updateThing(Thing(thing.getId(),_name,_place,null))
                if(status > -1){
                    Toast.makeText(applicationContext,"Record Updated",Toast.LENGTH_LONG).show()
                    updateDialog.dismiss()
                    reloadList()
                }
            }else{
                Toast.makeText(applicationContext,"Name or Place cannot be blank!",Toast.LENGTH_LONG).show()
            }
        })

        updateDialog.show()
    }


    fun backToMenu(view: View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun deleteThing(thing: Thing){
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Delete Thing")
        dialogBuilder.setMessage("Are you sure you wants to delete this record?")

        dialogBuilder.setPositiveButton("Yes"){DialogInterface, which ->
            val db: DataBaseManager = DataBaseManager(this)

            val status = db.deleteThing(thing)
            if(status > -1){
                Toast.makeText(applicationContext,
                        "Thing deleted",
                        Toast.LENGTH_LONG
                ).show()
                reloadList()
            }
        }

        dialogBuilder.setNegativeButton("No"){DialogInterface,which ->
            DialogInterface.dismiss()
        }

        val alertDialog: AlertDialog = dialogBuilder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    fun reloadList(){
        val list = generateList(thingCount())

        val recycler_view: RecyclerView = findViewById(R.id.recycle_view)
        recycler_view.adapter = ThingAdapter(this,list)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }
}