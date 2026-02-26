package com.example.todolist.home

//import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.base.BaseFragment
//import com.example.todolist.data.TaskWithTags
//import com.example.todolist.data.Task
import com.example.todolist.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import androidx.core.widget.addTextChangedListener

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: TaskAdapter

    override fun setupViews() {
        adapter = TaskAdapter { taskWithTags ->
            viewModel.delete(taskWithTags)
        }
        binding.recyclerViewTask.layoutManager =
            LinearLayoutManager(requireContext())
        binding.recyclerViewTask.adapter = adapter
        binding.addButton.setOnClickListener {
            onClick(it.id)
        }
        binding.clearAll.setOnClickListener {
            onClick(it.id)
        }
        binding.searchBar.addTextChangedListener { text ->
            val searchtext = "${text.toString()}%"
            lifecycleScope.launch {
                viewModel.searchtask(searchtext).collect { taskList ->
                    adapter.submitList(taskList)
                }
            }
        }
    }

    override fun observeViewModel() {
        viewModel.tasks.observe(viewLifecycleOwner) { taskList ->
            adapter.submitList(taskList)
        }
    }

    override fun onClick(viewId: Int) {
        when (viewId) {
            R.id.addButton -> {
                findNavController().navigate(R.id.home_To_add)
            }

            R.id.clearAll -> {
                viewModel.clearAll()
            }
        }
    }
}