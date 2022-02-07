package com.agungfir.taskmanager.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.agungfir.taskmanager.db.DbContract.DbContractTask.Companion.TABLE_TASK
import com.agungfir.taskmanager.db.DbContract.DbContractTask.Companion.TASK_COLOR
import com.agungfir.taskmanager.db.DbContract.DbContractTask.Companion.TASK_DATE
import com.agungfir.taskmanager.db.DbContract.DbContractTask.Companion.TASK_ID
import com.agungfir.taskmanager.db.DbContract.DbContractTask.Companion.TASK_IS_COMPLETE
import com.agungfir.taskmanager.db.DbContract.DbContractTask.Companion.TASK_LOCATION
import com.agungfir.taskmanager.db.DbContract.DbContractTask.Companion.TASK_TITLE
import com.agungfir.taskmanager.db.DbContract.DbContractTask.Companion.TASK_TYPE

class DbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "task_manager"
        const val DATABASE_VERSION = 1

        const val QUERY_CREATE_TABLE_TASK = "CREATE TABLE $TABLE_TASK" +
                " ($TASK_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $TASK_TITLE TEXT NOT NULL," +
                " $TASK_DATE TEXT," +
                " $TASK_LOCATION TEXT," +
                " $TASK_TYPE INTEGER," +
                " $TASK_COLOR INTEGER," +
                " $TASK_IS_COMPLETE INTEGER NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(QUERY_CREATE_TABLE_TASK)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_TASK")
        onCreate(db)
    }
}