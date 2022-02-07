package com.agungfir.taskmanager.db

object DbContract {
    class DbContractTask {
        companion object {
            const val TABLE_TASK = "task"
            const val TASK_ID = "task_id"
            const val TASK_TITLE = "task_title"
            const val TASK_DATE = "task_date"
            const val TASK_LOCATION = "task_location"
            const val TASK_TYPE = "task_type"
            const val TASK_COLOR = "task_color"
            const val TASK_IS_COMPLETE = "task_is_complete"
        }
    }
}