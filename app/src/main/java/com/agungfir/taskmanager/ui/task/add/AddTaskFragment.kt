package com.agungfir.taskmanager.ui.task.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.agungfir.taskmanager.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class AddTaskFragment : DialogFragment() {

    private val taskColors = arrayOf(
        R.color.yellow,
        R.color.teal,
        R.color.blueSky,
        R.color.purple_200,
        R.color.red,
        R.color.orange,
        R.color.blue,
        R.color.purple_400
    )

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
    private lateinit var textInputLayoutTitle: TextInputLayout

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
        btnClose = view.findViewById(R.id.btnClose)
        rvTaskColors = view.findViewById(R.id.rvTaskColors)
        typeTaskBasic = view.findViewById(R.id.typeTaskBasic)
        typeTaskUrgent = view.findViewById(R.id.typeTaskUrgent)
        typeTaskImportant = view.findViewById(R.id.typeTaskImportant)
        edtTitleTask = view.findViewById(R.id.edtTitleTask)
        textInputLayoutTitle = view.findViewById(R.id.textInputLayoutTitle)

        btnClose.setOnClickListener {
            this.dismiss()
        }

        val colorTaskAdapter = ColorTaskAdapter(taskColors)
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
    }

    private fun showColorSelected(color: Int) {
        textInputLayoutTitle.endIconDrawable?.setTint(
            ContextCompat.getColor(
                requireActivity(),
                color
            )
        )
    }

    private fun selectTypes(clickType: Int) {

        typesTask.forEachIndexed { index, materialButton ->
            materialButton.apply {
                if (clickType == index) {
//                    setBackgroundDrawable(
//                        ContextCompat.getDrawable(
//                            requireContext(),
//                            R.drawable.bg_circle_black
//                        )
//                    )
                    background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.bg_circle_black)
//                    setTextColor(resources.getColor(R.color.white))
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                } else {
//                    setBackgroundDrawable(
//                        ContextCompat.getDrawable(
//                            requireContext(),
//                            R.drawable.bg_cirlce_stroke_black
//                        )
//                    )
                    background =
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.bg_cirlce_stroke_black
                        )
//                    setTextColor(resources.getColor(R.color.black))
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                }
            }

        }
    }

}