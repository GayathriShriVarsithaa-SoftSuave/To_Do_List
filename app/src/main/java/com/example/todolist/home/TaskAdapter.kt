package com.example.todolist.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.Task
import com.example.todolist.data.TaskWithTags
import com.example.todolist.databinding.ItemTaskBinding

class TaskAdapter(
    private val onDeleteClick: (TaskWithTags) -> Unit
) : ListAdapter<TaskWithTags, TaskAdapter.TaskViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TaskWithTags) {
            binding.textTitle.text = item.task.title
            binding.textTags.text =
                item.tags.joinToString(", ") { it.tag }

            binding.deleteBtn.setOnClickListener {
                onDeleteClick(item)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<TaskWithTags>() {
        override fun areItemsTheSame(
            oldItem: TaskWithTags,
            newItem: TaskWithTags
        ): Boolean =
            oldItem.task.entryId == newItem.task.entryId

        override fun areContentsTheSame(
            oldItem: TaskWithTags,
            newItem: TaskWithTags
        ): Boolean =
            oldItem == newItem
    }
}