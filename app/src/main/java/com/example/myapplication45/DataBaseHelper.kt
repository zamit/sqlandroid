package com.example.myapplication45

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

    val DATABASENAME = "MY DATABASE"
    val TABLENAME = "Users"
    val COL_NAME = "name"
    val COL_AGE = "age"
    val COL_ID = "id"
class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASENAME, null,
        1) {
        override fun onCreate(db: SQLiteDatabase?) {
            val createTable = "CREATE TABLE " + TABLENAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + " VARCHAR(256)," + COL_AGE + " INTEGER)"
            db?.execSQL(createTable)
        }
        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            //onCreate(db);
        }
        fun insertData(user: User) {
            val database = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(COL_NAME, user.name)
            contentValues.put(COL_AGE, user.age)
            val result = database.insert(TABLENAME, null, contentValues)
            if (result == (0).toLong()) {
                Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(context, "Успешно", Toast.LENGTH_SHORT).show()
            }
        }
        fun readData(): MutableList<User> {
            val list: MutableList<User> = ArrayList()
            val db = this.readableDatabase
            var id: Int
            var name: String
            var age: Int
            var result: Cursor? = null
            val query = "Select * from $TABLENAME"
            result = db.rawQuery(query, null)
            if (result.moveToFirst()) {
                do {
                    id = result.getString(result.getColumnIndex(COL_ID))
                    name = result.getString(result.getColumnIndex(COL_NAME))
                    age = result.getString(result.getColumnIndex(COL_AGE))
                    list.add(User(id, name, age))
                }
                while (result.moveToNext())
            }
            return list
        }
    }




