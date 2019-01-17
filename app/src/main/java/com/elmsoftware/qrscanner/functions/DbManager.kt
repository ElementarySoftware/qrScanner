package com.elmsoftware.clever_note.functions

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.os.strictmode.SqliteObjectLeakedViolation
import android.util.Log
import android.widget.Toast
import kotlin.reflect.KTypeProjection

class DbManager{
    //database
    var dbName = "MyCards"
    //table
    var dbTable = "Cards"
    var dbTableItem = "Items"
    //columns
    var colID = "ID"
    var colTitle = "Title"
    var colValue = "Value"
    var itemCheck = "Sel"
    var item_id = "ITEM_ID"
    //database version
    var dbVersion = 1

    //CREATE TABLE
    var sqlCreateTable = "CREATE TABLE IF NOT EXISTS "+dbTable+" ("+colID+" INTEGER PRIMARY KEY, "+ colTitle +" TEXT, "+ colValue + " TEXT);"
    var sqlDB:SQLiteDatabase?=null

    constructor(context: Context){
        var db = DatabaseHelperNotes(context)
        sqlDB = db.writableDatabase
    }

    inner class DatabaseHelperNotes:SQLiteOpenHelper{
        var context: Context?=null

        constructor(context: Context):super(context,dbName,null,dbVersion){
            this.context = context
        }

        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(sqlCreateTable)
            Toast.makeText(this.context, "database created...",Toast.LENGTH_SHORT).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("Drop Table if Exists" + dbTable)
        }

    }

    fun insert(values:ContentValues):Long{
        val ID = sqlDB!!.insert(dbTable,"",values)
        return ID
    }

    fun insert_item(values:ContentValues):Long{
        val ID = sqlDB!!.insert(dbTableItem,"",values)
        return ID
    }

    fun Query(projection: Array<String>, selection: String, selectionArgs: Array<String>, sorOrder:String): Cursor {
        val qb = SQLiteQueryBuilder()
        qb.tables = dbTable
        val cursor = qb.query(sqlDB, projection, selection, selectionArgs, null, null, sorOrder)
        return cursor
    }

    fun Query_item(projection: Array<String>, selection: String, selectionArgs: Array<String>, sorOrder:String): Cursor {
        val qb = SQLiteQueryBuilder()
        qb.tables = dbTableItem
        val cursor = qb.query(sqlDB, projection, null, null, null, null, null)
        return cursor
    }

    fun delete(selection: String, selectionArgs: Array<String>):Int{
        val count = sqlDB!!.delete(dbTable, selection, selectionArgs)
        return count
    }

    fun delete_item(selection: String, selectionArgs: Array<String>):Int{
        val count = sqlDB!!.delete(dbTableItem, selection, selectionArgs)
        return count
    }

    fun update(values: ContentValues, selection: String, selectionArgs: Array<String>):Int{
        val count = sqlDB!!.update(dbTable, values, selection, selectionArgs)
        return count
    }

    fun update_item(values: ContentValues, selection: String, selectionArgs: Array<String>):Int{
        val count = sqlDB!!.update(dbTableItem, values, selection, selectionArgs)
        return count
    }

    fun rearange(values: ContentValues, values2: ContentValues, selection: String, selectionArgs: Array<String>, selectionArgs2: Array<String>):Int{
        sqlDB!!.update(dbTable, values, selection, selectionArgs)
        sqlDB!!.update(dbTable, values2, selection, selectionArgs2)
        return 1
    }

}