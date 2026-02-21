package com.example.todolist.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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

        binding.recyclerViewTask.adapter = adapter
        binding.recyclerViewTask.layoutManager =
            LinearLayoutManager(requireContext())

        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.)
//            val task = Task(
//                id = 0,
//                title = "Sample",
//                tags = "android,kotlin",
//                description = "Test description"
//            )
//            viewModel.insert(task)
        }
        binding.clearAll.setOnClickListener {
            viewModel.clearAll()
        }
    }

    override fun observeViewModel() {
        viewModel.allTasks.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onClick(viewId: Int) {

    }
}