package com.example.thingstracker

class Thing {
    private lateinit var name: String
    private lateinit var place: String
    private lateinit var date: String
    private lateinit var time: String

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