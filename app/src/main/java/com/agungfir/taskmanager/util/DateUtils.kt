package com.agungfir.taskmanager.util

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import com.agungfir.taskmanager.R
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun showDataPicker(context: Context, onDateSetListener: DatePickerDialog.OnDateSetListener) {
        // get current date
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            context,
            R.style.MaterialCalendarTheme,
            onDateSetListener,
            year,
            month,
            day
        ).show()
    }

    fun dateFormatSql(year: Int, month: Int, dayOfMonth: Int): String = "$year-$month-$dayOfMonth"

    fun dateFromSqlToDateViewTask(rawDate: String): String {
        var result = ""
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(rawDate)

        if (date != null) {
            result = SimpleDateFormat("EEE, dd MM yyyy", Locale.getDefault()).format(date)
        }

        return result
    }

    @SuppressLint("SimpleDateFormat")
    fun dateStringToTimestamp(dateString: String): Long {
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateString)
        return date.time!!
    }
}