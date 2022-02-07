package com.agungfir.taskmanager.ui.task.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agungfir.taskmanager.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailTaskFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(container?.context)
            .inflate(R.layout.fragment_detail_task, container, false)
        return view
    }

    companion object {
        val TAG = DetailTaskFragment::class.java.simpleName
    }
}