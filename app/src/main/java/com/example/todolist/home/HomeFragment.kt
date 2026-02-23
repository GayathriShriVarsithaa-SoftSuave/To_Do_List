package com.example.todolist.home

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
        adapter = TaskAdapter()

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
    }

    override fun onClick(viewId: Int) {
        when (viewId) {
            binding.addButton.id -> {
                findNavController().navigate(R.id.home_To_add)
            }

            R.id.recyclerViewTask -> {
                adapter = TaskAdapter(
                    onItemClick = { task: Task ->
                        val action = HomeFragmentDirections.homeToDetail(task.id)
                        findNavController().navigate(action)
                    },
                    onDeleteClick = { task: Task ->
                        viewModel.delete(task)
                    }
                )
                binding.recyclerViewTask.adapter = adapter
            }
        }
    }
}