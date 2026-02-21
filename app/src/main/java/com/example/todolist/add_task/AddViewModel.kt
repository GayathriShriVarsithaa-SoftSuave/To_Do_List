package com.example.todolist.add_task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.AppDatabase
import com.example.todolist.data.Task
import com.example.todolist.data.TaskRepository
import kotlinx.coroutines.launch

class AddViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).taskDao()
    private val repository = TaskRepository(dao)

    fun insert(task: Task) {
        viewModelScope.launch {
            repository.insert(task)
        }
    }
}