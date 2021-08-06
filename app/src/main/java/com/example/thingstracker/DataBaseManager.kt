package com.example.thingstracker

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

 class DataBaseManager(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "ThingsDatabase"
        private const val TABLE = "things"

        private const val  id = "id"
        private const val  name = "name"
        private const val place = "place"
        private const val date = "date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createThingsTable = ("CREATE TABLE $TABLE($id INTEGER PRIMARY KEY, $name TEXT, $place TEXT, $date TEXT)")
        db?.execSQL(createThingsTable)
    }

     override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
         db!!.execSQL("DROP TABLE IF EXISTS $TABLE")
         onCreate(db)
     }

     fun addThing(thing: Thing) : Long{
         val db = this.writableDatabase

         val contentValues = ContentValues()
         contentValues.put(name,thing.getName())
         contentValues.put(place,thing.getPlace())
         contentValues.put(date,thing.getDate())

         val success = db.insert(TABLE,null,contentValues)

         db.close()
         return success
     }

     fun viewThings(): ArrayList<Thing>{
         val things: ArrayList<Thing> = ArrayList<Thing>()

         val query = "SELECT * FROM $TABLE"

         val db = this.readableDatabase
         var cursor: Cursor? = null

         try{
             cursor = db.rawQuery(query,null)
         }catch (e: SQLiteException){
             db.execSQL(query)
             return ArrayList()
         }

         var _id: Int
         var _name: String
         var _place: String
         var _date: String

         if(cursor.moveToFirst()){
             do{
                 _id = cursor.getInt(cursor.getColumnIndex(id))
                 _name = cursor.getString(cursor.getColumnIndex(name))
                 _place = cursor.getString(cursor.getColumnIndex(place))
                 _date = cursor.getString(cursor.getColumnIndex(date))

                 val thing = Thing(_id,_name,_place,_date)
                 things.add(thing)
             }while (cursor.moveToNext())
         }
         return things
     }

     fun updateThing(thing: Thing): Int{
         val db = this.writableDatabase

         val contentValues = ContentValues()
         contentValues.put(id,thing.getId())
         contentValues.put(name,thing.getName())
         contentValues.put(place,thing.getPlace())
         contentValues.put(date,thing.getDate())


         val success = db.update(TABLE,contentValues,id + "=" + thing.getId(),null)

         db.close()
         return success
     }

     fun deleteThing(thing: Thing): Int{
         val db = this.writableDatabase

         val contentValues = ContentValues()
         contentValues.put(id,thing.getId())

         val success = db.delete(TABLE,id + "=" + thing.getId(),null)
         db.close()

         return success
     }
}