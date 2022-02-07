package com.agungfir.taskmanager.ui.main.done

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.agungfir.taskmanager.R


class TaskDoneFragment : Fragment() {

    private lateinit var rvTaskDone: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            LayoutInflater.from(parent?.context).inflate(R.layout.fragment_task_done, parent, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTaskDone = view.findViewById(R.id.rvTaskDone)
        rvTaskDone.adapter = TaskDoneAdapter()
    }

}