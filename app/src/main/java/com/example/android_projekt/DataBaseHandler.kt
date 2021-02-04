package com.example.android_projekt

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "ProfileDB"
val TABLE_NAME = "Profiles"
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
                COL_NAME + " TEXT," +
                COL_ADRESS + " TEXT," +
                COL_PHONE_NUMBER + " INTEGER," +
                COL_EMAIL + " TEXT)"


        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?,oldVersion: Int,newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertData(profile : Profile){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NAME,profile.name)
        cv.put(COL_ADRESS,profile.adress)
        cv.put(COL_PHONE_NUMBER,profile.phone_number)
        cv.put(COL_EMAIL,profile.email)
        val result = db.insert(TABLE_NAME,null,cv)
        if(result == (-1).toLong())
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
    }

    fun readData() : MutableList<Profile>{
        val list : MutableList<Profile> = ArrayList()

        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                val profile = Profile()
                profile.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                profile.name = result.getString(result.getColumnIndex(COL_NAME))
                profile.adress = result.getString(result.getColumnIndex(COL_ADRESS))
                profile.email = result.getString(result.getColumnIndex(COL_EMAIL))
                profile.phone_number = result.getString(result.getColumnIndex(COL_PHONE_NUMBER)).toInt()
                list.add(profile)
            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    fun deleteData(){
        val db = this.writableDatabase
        db.delete(TABLE_NAME,null,null)
        db.close()
    }


}
