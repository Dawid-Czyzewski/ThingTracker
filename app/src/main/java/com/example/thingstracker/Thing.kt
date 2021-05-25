package com.example.thingstracker

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class Thing(_name: String, _place: String) {
    private  var name: String = _name
    private  var place: String = _place
    private  var date: String
    private  var time: String

    init {
        val currentDateTime = LocalDateTime.now()
        date = currentDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))
        time = currentDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))
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

    fun  getDate(): String {
        return date
    }
    fun  setDate(_date: String) {
        date = _date
    }

    fun  getTime(): String {
        return time
    }
    fun  setTime(_time: String) {
        time = _time
    }
}