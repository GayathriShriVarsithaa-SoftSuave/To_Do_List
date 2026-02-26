package com.example.todolist.home

import android.app.Application
import androidx.lifecycle.*
import com.example.todolist.data.AppDatabase
import com.example.todolist.data.TaskWithTags
import com.example.todolist.data.TaskRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).taskDao()
    private val repository = TaskRepository(dao)
    val tasks: LiveData<List<TaskWithTags>> =
        repository.taskwithTags.asLiveData()

    fun clearAll() {
        viewModelScope.launch {
            repository.clearAll()
        }
    }

    fun delete(taskWithTags: TaskWithTags) {
        viewModelScope.launch {
            repository.delete(taskWithTags.task)
        }
    }
}