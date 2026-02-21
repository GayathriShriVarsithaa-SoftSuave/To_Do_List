package com.example.todolist.home

import android.app.Application
import androidx.lifecycle.*
import com.example.todolist.data.AppDatabase
import com.example.todolist.data.Task
import com.example.todolist.data.TaskRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).taskDao()
    private val repository = TaskRepository(dao)

    val allTasks = repository.allTasks.asLiveData()

    fun insert(task: Task) {
        viewModelScope.launch {
            repository.insert(task)
        }
    }
}