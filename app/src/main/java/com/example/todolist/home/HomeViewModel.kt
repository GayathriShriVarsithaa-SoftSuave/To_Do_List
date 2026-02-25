package com.example.todolist.home

import android.app.Application
import androidx.lifecycle.*
import com.example.todolist.data.AppDatabase
import com.example.todolist.data.Task
import com.example.todolist.data.TaskRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).toDoListDao()
    private val repository = TaskRepository(dao)
    val tasks: LiveData<List<Task>> =
        repository.getAllTasks().asLiveData()

    fun clearAll() {
        viewModelScope.launch {
            repository.clearAll()
        }
    }

    fun delete(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }
}