package com.example.android_projekt

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "ProfileDB"
val TABLE_NAME = "Profile"
val COL_NAME = "name"
val COL_ADRESS = "adress"
val COL_PHONE_NUMBER = "phone_number"
val COL_EMAIL = "email"
val COL_ID = "id"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1)
{
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME +" (" +
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(256)," +
                COL_ADRESS + " VARCHAR(256)," +
                COL_PHONE_NUMBER + " INTEGER" +
                COL_EMAIL + " VARCHAR(256))"

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?,oldVersion: Int,newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertData(profile : Profile){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME,profile.name)
        cv.put(COL_ADRESS,profile.adress)
        cv.put(COL_PHONE_NUMBER,profile.phone_number)
        cv.put(COL_EMAIL,profile.email)
        var result = db.insert(TABLE_NAME,null,cv)
        if(result == -1.toLong())
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
    }
}