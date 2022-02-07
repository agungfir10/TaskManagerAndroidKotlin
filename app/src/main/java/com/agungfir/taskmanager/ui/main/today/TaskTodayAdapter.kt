package com.agungfir.taskmanager.ui.main.today

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agungfir.taskmanager.R

class TaskTodayAdapter : RecyclerView.Adapter<TaskTodayAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemCallback

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task_today, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked()
        }
    }

    override fun getItemCount(): Int = 10

    interface OnItemCallback {
        fun onItemClicked()
    }
}