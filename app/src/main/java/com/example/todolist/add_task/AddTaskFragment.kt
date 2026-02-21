package com.example.todolist.add_task

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.base.BaseFragment
import com.example.todolist.data.Task
import com.example.todolist.databinding.FragmentAddTaskBinding

class AddTaskFragment : BaseFragment<FragmentAddTaskBinding>(
    FragmentAddTaskBinding::inflate
) {

    private val viewModel: AddViewModel by viewModels()

    override fun setupViews() {

        binding.addBtn.setOnClickListener {

            val title = binding.titleEdit.text.toString()
            val tags = binding.tagEdit.text.toString()
            val description = binding.descriptionEdit.text.toString()

            if (title.isNotBlank()) {

                val task = Task(
                    id = 0,
                    title = title,
                    tags = tags,
                    description = description
                )
                viewModel.insert(task)
                findNavController().popBackStack()
            }
        }
    }

    override fun observeViewModel() {}
    override fun onClick(viewId: Int) {}
}