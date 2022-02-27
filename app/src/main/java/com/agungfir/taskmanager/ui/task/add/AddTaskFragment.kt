package com.agungfir.taskmanager.ui.task.add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.agungfir.taskmanager.R
import com.agungfir.taskmanager.db.DbTaskHelper
import com.agungfir.taskmanager.model.Task
import com.agungfir.taskmanager.util.DateUtils
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class AddTaskFragment : DialogFragment() {

    companion object {
        const val TAG = "AddTaskFragment"
    }

    private lateinit var typesTask: MutableList<MaterialButton>
    private lateinit var btnClose: ImageButton
    private lateinit var rvTaskColors: RecyclerView
    private lateinit var typeTaskBasic: MaterialButton
    private lateinit var typeTaskUrgent: MaterialButton
    private lateinit var typeTaskImportant: MaterialButton
    private lateinit var edtTitleTask: EditText
    private lateinit var edtDeadlineTask: EditText
    private lateinit var textInputLayoutTitle: TextInputLayout
    private lateinit var btnSaveTask: Button

    private lateinit var dbTaskHelper: DbTaskHelper
    private var isEdit = false
    private var task: Task? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullscreenDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        setupDB()
        onClick()
    }

    private fun onClick() {
        btnClose.setOnClickListener {
            this.dismiss()
        }

        val colorTaskAdapter = ColorTaskAdapter(resources.getIntArray(R.array.task_colors))
        rvTaskColors.apply {
            adapter = colorTaskAdapter
        }
        colorTaskAdapter.setOnItemClickCallback(object : ColorTaskAdapter.OnItemClickCallback {
            override fun onItemClicked(color: Int) {
                showColorSelected(color)
            }
        })

        typesTask = mutableListOf(
            typeTaskBasic,
            typeTaskUrgent,
            typeTaskImportant,
        )
        typesTask.forEachIndexed { index, materialButton ->
            materialButton.setOnClickListener {
                selectTypes(index)
            }
        }

        edtDeadlineTask.setOnClickListener {
            DateUtils.showDataPicker(requireContext()) { _, year, month, dayOfMonth ->
                val dateString = DateUtils.dateFormatSql(year, month, dayOfMonth)
                edtDeadlineTask.setText(DateUtils.dateFromSqlToDateViewTask(dateString))

                task?.date = dateString
                task?.timestamp = DateUtils.dateStringToTimestamp(dateString)

                Log.i("DATE", dateString)
                Log.i("TIMESTAMP", task?.timestamp.toString())
            }
        }

        btnSaveTask.setOnClickListener {
            val title = edtTitleTask.text.toString()

        }
    }

    private fun setupDB() {
        dbTaskHelper = DbTaskHelper.getInstance(requireContext())
    }

    private fun initView(view: View) {
        btnClose = view.findViewById(R.id.btnClose)
        rvTaskColors = view.findViewById(R.id.rvTaskColors)
        typeTaskBasic = view.findViewById(R.id.typeTaskBasic)
        typeTaskUrgent = view.findViewById(R.id.typeTaskUrgent)
        typeTaskImportant = view.findViewById(R.id.typeTaskImportant)
        edtTitleTask = view.findViewById(R.id.edtTitleTask)
        edtDeadlineTask = view.findViewById(R.id.edtDeadlineTask)
        textInputLayoutTitle = view.findViewById(R.id.textInputLayoutTitle)
        btnSaveTask = view.findViewById(R.id.btnSaveTask)
    }

    private fun showColorSelected(color: Int) {
        textInputLayoutTitle.endIconDrawable?.setTint(color)
    }

    private fun selectTypes(clickType: Int) {
        typesTask.forEachIndexed { index, materialButton ->
            materialButton.apply {
                if (clickType == index) {
                    background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.bg_circle_black)
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                } else {
                    background =
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.bg_cirlce_stroke_black
                        )
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                }
            }

        }
    }

}