package com.example.thingstracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thingstracker.Activities.ThingsViewActivity

class ThingAdapter(private val context: Context, private val thingList: List<Thing>): RecyclerView.Adapter<ThingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val thingView = LayoutInflater.from(parent.context).inflate(R.layout.thing_element_list,
        parent, false)

        return ViewHolder(thingView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentThing = thingList[position]

        holder.textView1.text = currentThing.getName()
        holder.textView2.text = currentThing.getPlace()
        holder.textView3.text = currentThing.getDate()
        holder.edit_button.setOnClickListener{view ->
            if(context is ThingsViewActivity){
                context.editThing(currentThing)
            }
        }
        holder.delete_button.setOnClickListener{view ->
            if(context is ThingsViewActivity){
                context.deleteThing(currentThing)
            }
        }
    }

    override fun getItemCount() = thingList.size

    class ViewHolder(thingView: View): RecyclerView.ViewHolder(thingView){
        val edit_button: ImageButton = thingView.findViewById(R.id._editButton)
        val delete_button: ImageButton = thingView.findViewById(R.id.delete_button)
        val textView1: TextView = thingView.findViewById(R.id.text1)
        val textView2: TextView = thingView.findViewById(R.id.text2)
        val textView3: TextView = thingView.findViewById(R.id.text3)
    }
}