package com.agungfir.taskmanager.ui.task.add

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.agungfir.taskmanager.R

class ColorTaskAdapter(private val taskColors: Array<Int>) :
    RecyclerView.Adapter<ColorTaskAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivColorTask = itemView.findViewById<ImageView>(R.id.ivColorTask)

        fun bind(i: Int) {
            ivColorTask.setColorFilter(
                ContextCompat.getColor(itemView.context, i)
            )
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task_color, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(taskColors[position])
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(taskColors[position])
        }
    }

    override fun getItemCount(): Int = taskColors.size

    interface OnItemClickCallback {

        fun onItemClicked(color: Int)

    }
}