package com.example.thingstracker

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class Thing(_id: Int, _name: String, _place: String, _date: String?) {
    private  var name: String = _name
    private  var place: String = _place
    private  var date: String?
    private var id: Int = _id

    init {
        if(_date == null){
            val currentDateTime = LocalDateTime.now()
            date = currentDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))
        }else{
            date = _date
        }

    }

    fun  getName(): String {
        return name
    }
    fun  setName(_name: String) {
        name = _name
    }

    fun  getPlace(): String {
        return place
    }
    fun  setPlace(_place: String) {
        place = _place
    }

    fun  getDate(): String? {
        return date
    }
    fun  setDate(_date: String) {
        date = _date
    }
    fun getId(): Int{
        return id
    }
    fun  setId(_id: Int) {
        id = _id
    }
}