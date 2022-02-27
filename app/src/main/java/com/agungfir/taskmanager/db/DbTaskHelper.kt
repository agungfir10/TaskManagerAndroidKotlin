package com.agungfir.taskmanager.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.agungfir.taskmanager.db.DbContract.DbContractTask.Companion.TABLE_TASK
import com.agungfir.taskmanager.db.DbContract.DbContractTask.Companion.TASK_COLOR
import com.agungfir.taskmanager.db.DbContract.DbContractTask.Companion.TASK_CREATED_AT
import com.agungfir.taskmanager.db.DbContract.DbContractTask.Companion.TASK_DATE
import com.agungfir.taskmanager.db.DbContract.DbContractTask.Companion.TASK_ID
import com.agungfir.taskmanager.db.DbContract.DbContractTask.Companion.TASK_IS_COMPLETE
import com.agungfir.taskmanager.db.DbContract.DbContractTask.Companion.TASK_LOCATION
import com.agungfir.taskmanager.db.DbContract.DbContractTask.Companion.TASK_TITLE
import com.agungfir.taskmanager.db.DbContract.DbContractTask.Companion.TASK_TYPE
import com.agungfir.taskmanager.db.DbContract.DbContractTask.Companion.TASK_UPDATED_AT
import com.agungfir.taskmanager.model.Task

class DbTaskHelper(context: Context?) {

    companion object {
        private const val TABLE_NAME = TABLE_TASK
        private lateinit var dbHelper: DbHelper
        private lateinit var db: SQLiteDatabase

        private var instance: DbTaskHelper? = null

        fun getInstance(context: Context?): DbTaskHelper {
            if (instance == null) {
                synchronized(SQLiteOpenHelper::class.java) {
                    if (instance == null) {
                        instance = DbTaskHelper(context)
                    }
                }
            }
            return instance as DbTaskHelper
        }
    }

    init {
        dbHelper = DbHelper(context)
    }

    private fun open() {
        db = dbHelper.writableDatabase
    }

    private fun close() {
        dbHelper.close()

        if (db.isOpen) {
            db.close()
        }
    }

    private fun insertTask(task: Task?): Long {
        open()
        val values = ContentValues()
        values.put(TASK_TITLE, task?.title)
        values.put(TASK_DATE, task?.date)
        values.put(TASK_LOCATION, task?.location)
        values.put(TASK_TYPE, task?.type)
        values.put(TASK_COLOR, task?.taskColor)
        values.put(TASK_CREATED_AT, task?.createdAt)
        values.put(TASK_UPDATED_AT, task?.updatedAt)

        val result = db.insert(TABLE_NAME, null, values)

        close()
        return result
    }

    fun getAllTaskToday(timestampToday: Long?): List<Task>? {
        open()
        val tasks = mutableListOf<Task>()
        val query =
            "SELECT * FROM $TABLE_NAME WHERE $TASK_IS_COMPLETE = 0 AND $TASK_DATE = $timestampToday"
        val cursor = db.rawQuery(query, null)
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(TASK_ID))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(TASK_TITLE))
                val date = cursor.getLong(cursor.getColumnIndexOrThrow(TASK_DATE))
                val location = cursor.getString(cursor.getColumnIndexOrThrow(TASK_LOCATION))
                val type = cursor.getInt(cursor.getColumnIndexOrThrow(TASK_TYPE))
                val taskColor = cursor.getInt(cursor.getColumnIndexOrThrow(TASK_COLOR))
                val isComplete = cursor.getInt(cursor.getColumnIndexOrThrow(TASK_IS_COMPLETE))
            }
        }
        close()
        return null
    }

}