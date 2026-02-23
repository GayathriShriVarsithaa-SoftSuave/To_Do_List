package com.example.todolist.add_task

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentAddTaskBinding

class AddTaskFragment : BaseFragment<FragmentAddTaskBinding>(
    FragmentAddTaskBinding::inflate
) {

    private val viewModel: AddViewModel by viewModels()

    override fun setupViews() {
        binding.addBtn.setOnClickListener {
            onClick(it.id)
        }
    }

    override fun observeViewModel() {
        viewModel.message.observe(viewLifecycleOwner) { message ->
            binding.titleEdit.error = message
        }

        viewModel.taskAdded.observe(viewLifecycleOwner) { added ->
            if (added) {
                findNavController().popBackStack()
                viewModel.resetTaskAdded()
            }
        }
    }

    override fun onClick(viewId: Int) {
        when (viewId) {
            R.id.addBtn -> {
                binding.titleEdit.error = null

                val title = binding.titleEdit.text.toString().trim()
                val tags = binding.tagEdit.text.toString().trim()
                val description = binding.descriptionEdit.text.toString().trim()

                viewModel.addTask(title, tags, description)
            }
        }
    }
}