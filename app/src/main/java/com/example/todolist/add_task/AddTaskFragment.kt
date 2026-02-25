package com.example.todolist.add_task

//import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
//import androidx.lifecycle.findViewTreeLifecycleOwner
//import androidx.navigation.fragment.findNavController
//import com.example.todolist.R
import com.example.todolist.base.BaseFragment
import com.example.todolist.data.Tag
import com.example.todolist.databinding.FragmentAddTaskBinding

class AddTaskFragment : BaseFragment<FragmentAddTaskBinding>(
    FragmentAddTaskBinding::inflate
) {
    private val viewModel: AddViewModel by viewModels()
    private val selectedTags = mutableSetOf<Tag>()

    override fun setupViews() {
        binding.addBtn.setOnClickListener {
            onClick(it.id)
        }
        binding.workTag.setOnClickListener {
            onClick(it.id)
        }
        binding.relaxTag.setOnClickListener {
            onClick(it.id)
        }
        binding.urgentTag.setOnClickListener {
            onClick(it.id)
        }
    }

    override fun observeViewModel() {
        viewModel.taskAdded.observe(viewLifecycleOwner) { added ->
            if (added) {
                Toast.makeText(requireContext(), "Task added!", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
                viewModel.resetTaskAdded()
            }
        }
        viewModel.message.observe(viewLifecycleOwner) { msg ->
            msg?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(viewId: Int) {
        when (viewId) {
            R.id.addBtn -> {
                val title = binding.titleEdit.text.toString().trim()

                if (title.isEmpty()) {
                    Toast.makeText(requireContext(), "Enter a title", Toast.LENGTH_SHORT).show()
                    return
                }
                if (selectedTags.isEmpty()) {
                    Toast.makeText(requireContext(), "Select at least one tag", Toast.LENGTH_SHORT)
                        .show()
                    return
                }
                viewModel.addTaskWithTags(title, selectedTags.toList())
            }

            R.id.workTag -> {
                binding.workTag.isSelected = !binding.workTag.isSelected
                if (binding.workTag.isSelected) {
                    selectedTags.add(Tag(tag = "Work"))
                } else {
                    selectedTags.remove(Tag(tag = "Work"))
                }
            }

            R.id.urgentTag -> {
                binding.urgentTag.isSelected = !binding.urgentTag.isSelected
                if (binding.urgentTag.isSelected) {
                    selectedTags.add(Tag(tag = "Urgent"))
                } else {
                    selectedTags.remove(Tag(tag = "Urgent"))
                }
            }

            R.id.relaxTag -> {
                binding.relaxTag.isSelected = !binding.relaxTag.isSelected
                if (binding.relaxTag.isSelected) {
                    selectedTags.add(Tag(tag = "Relax"))
                } else {
                    selectedTags.remove(Tag(tag = "Relax"))
                }
            }
        }
    }
}