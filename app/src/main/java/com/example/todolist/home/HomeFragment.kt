package com.example.todolist.home

import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.base.BaseFragment
import com.example.todolist.data.Task
import com.example.todolist.databinding.FragmentHomeBinding
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: TaskAdapter

    override fun setupViews() {
        adapter = TaskAdapter(
            onItemClick = { task: Task ->
                val action = HomeFragmentDirections.homeToDetail(task.id)
                findNavController().navigate(action)
            },
            onDeleteClick = { task: Task ->
                viewModel.delete(task)
            }
        )
        binding.recyclerViewTask.layoutManager =
            LinearLayoutManager(requireContext())
        binding.recyclerViewTask.adapter = adapter

        binding.addButton.setOnClickListener {
            onClick(it.id)
        }

        binding.clearAll.setOnClickListener {
            viewModel.clearAll()
        }
    }

    override fun observeViewModel() {
        viewModel.allTasks.observe(viewLifecycleOwner) { tasks ->
            adapter.submitList(tasks)
        }
        viewModel.allTasks.observe(viewLifecycleOwner) { tasks ->
            val displayList=tasks.map{"${it.title} (${it.tags})"}
            val taskMap = tasks.associateBy { "${it.title} (${it.tags})" }
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                displayList
            )
            binding.autoCompleteSearch.setAdapter(adapter)
            binding.autoCompleteSearch.setOnItemClickListener{parent, view, position, id->
                val selectedStr=parent.getItemAtPosition(position) as String
                val selectedTask=taskMap[selectedStr]
                selectedTask?.let {
                    val action=HomeFragmentDirections.homeToDetail(it.id)
                    findNavController().navigate(action)
                }
                binding.autoCompleteSearch.setText("")
            }
        }
    }

    override fun onClick(viewId: Int) {
        when (viewId) {
            binding.addButton.id -> {
                findNavController().navigate(R.id.home_To_add)
            }
        }
    }
}