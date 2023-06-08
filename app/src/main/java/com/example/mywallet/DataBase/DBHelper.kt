package com.example.mywallet.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mywallet.Fragment.ChartFragment
import com.example.mywallet.Model.DataModel

class DBHelper(
    context: Context?,

) : SQLiteOpenHelper(context, "data.db", null, 1)

{

    override fun onCreate(db: SQLiteDatabase?) {
        var sql =
            "CREATE TABLE trans(id INTEGER PRIMARY KEY AUTOINCREMENT,amount INTEGER,category TEXT,note TEXT,isexpence INTEGER)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun addtransa(trans: DataModel) {

        var db = writableDatabase
        var values = ContentValues().apply {
            trans.apply {
                put("amount", amount)
                put("category", category)
                put("note", note)
                put("isexpence", isexpence)
            }
        }
        db.insert("trans", null, values)
    }

    fun getTransaction(): ArrayList<DataModel> {
        var transList = ArrayList<DataModel>()
        var db = readableDatabase
        var sql = "SELECT * FROM trans"
        var cursor = db.rawQuery(sql, null)
        cursor.moveToFirst()

        for (i in 0..cursor.count - 1) {
            var id = cursor.getInt(0)
            var amt = cursor.getInt(1)
            var note = cursor.getString(3)
            var category = cursor.getString(2)
            var isexpence = cursor.getInt(4)
            var data = DataModel(id, amt, note,category, isexpence)
            transList.add(data)
            cursor.moveToNext()
        }
        return transList
    }

    fun deleteTransaction(id: Int) {


        var db = writableDatabase
        var sql = "DELETE FROM trans WHERE id='$id'"

        db.execSQL(sql)
    }

    fun updateTransaction(trans: DataModel) {
        var db = writableDatabase
        var values = ContentValues().apply {
            trans.apply {
                put("amt", amount)
                put("category",category)
                put("note", note)
                put("isexpense", isexpence)
            }
        }

        db.update("trans", values, "id=${trans.id}", null)
    }
}