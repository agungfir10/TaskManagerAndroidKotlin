package com.agungfir.taskmanager.ui.main.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.agungfir.taskmanager.R
import com.agungfir.taskmanager.ui.task.detail.DetailTaskFragment

class TaskTodayFragment : Fragment() {

    private lateinit var rvTaskToday: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(container?.context)
            .inflate(R.layout.fragment_task_today, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TaskTodayAdapter()
        rvTaskToday = view.findViewById(R.id.rvTaskToday)
        rvTaskToday.adapter = adapter
        adapter.setOnItemClickCallback(object : TaskTodayAdapter.OnItemCallback {
            override fun onItemClicked() {
                val detailTaskFragment = DetailTaskFragment()
                detailTaskFragment.show(childFragmentManager, DetailTaskFragment.TAG)
            }
        })
    }
}