package com.example.nybaiboly

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelperLocal(context: Context) : SQLiteOpenHelper(context, Constants.DB_NAME, null, Constants.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Constants.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME)
        onCreate(db)
    }

    companion object {
        private var instance: DatabaseHelperLocal? = null
        fun getInstance(context: Context): DatabaseHelperLocal {
            if (instance == null) {
                instance = DatabaseHelperLocal(context)
            }
            return instance!!
        }
    }

    fun insertInfo(text: String, idlivre: String, idchapitre: String): Long {
        val db = writableDatabase
        val values = ContentValues()
        values.put(Constants.C_TEXT, text)
        values.put(Constants.C_ID_LIVRE, idlivre)
        values.put(Constants.C_ID_CHAPITRE, idchapitre)
        val id = db.insert(Constants.TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun deleteInfo(id: String) {
        try {
            val db = writableDatabase
            db.delete(Constants.TABLE_NAME, Constants.C_ID + " = ?", arrayOf(id))
            db.close()
        } catch (e: Exception) {
            println(e)
        }
    }

    fun getAllData(): ArrayList<Recent> {
        val arrayList = ArrayList<Recent>()

        val selectQuery = "SELECT * FROM ${Constants.TABLE_NAME} order by ${Constants.C_ID} desc"
        val db = writableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToNext()) {
            do {
                val model = Recent(
                    "" + cursor.getInt(0),
                    "" + cursor.getString(1),
                    "" + cursor.getString(2),
                    "" + cursor.getString(3)
                )
                arrayList.add(model)
            } while (cursor.moveToNext())
        }

        db.close()


        return arrayList
    }
}
