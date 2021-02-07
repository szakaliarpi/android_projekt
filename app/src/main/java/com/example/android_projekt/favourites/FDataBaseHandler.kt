package com.example.android_projekt.favourites

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.android_projekt.profile.COL_IMG

val DATABASE_NAME = "FavouritesDataBase"
val TABLE_NAME = "Favourites"
val COL_FID = "fid"
val COL_ID = "id"
val COL_NAME = "name"
val COL_IMAGE = "ImgSrcUrl"


class FDataBaseHandler(val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1){ //NULL VALUE IS THE CURSOR FACTORY

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME +" (" +
                COL_FID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_ID +" INTEGER ," +
                COL_NAME + " TEXT," +
                COL_IMAGE + " TEXT)"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    fun insertDataFavorites(favourites: Favourites){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_ID, favourites.id)


        val  result =  db?.insert(TABLE_NAME, null, cv)

    }

    fun readDataFavorites() : MutableList<Favourites>{
        val list : MutableList<Favourites> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()){
            do{
                val favourites = Favourites()
                favourites.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                favourites.name = result.getString(result.getColumnIndex(COL_NAME))

                list.add(favourites)
            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    fun deleteDataFavorites(num: Int){
        val db = this.writableDatabase
        db.delete(TABLE_NAME, COL_ID + "=?", arrayOf(num.toString()))
        db.close()
    }


    fun favoritesCheckedId(num: Int): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do{
                if (num == result.getString(result.getColumnIndex(com.example.android_projekt.favourites.COL_ID)).toInt()){
                    return true
                }
            }while (result.moveToNext())
        }
        return false
    }
}