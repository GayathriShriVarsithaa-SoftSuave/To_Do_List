package com.example.todolist.home

import android.app.Application
import androidx.lifecycle.*
import com.example.todolist.data.AppDatabase
import com.example.todolist.data.TaskWithTags
import com.example.todolist.data.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)

    private val taskDao = database.taskDao()
    private val tagDao = database.tagDao()

    private val repository = TaskRepository(taskDao, tagDao)
    val tasks: LiveData<List<TaskWithTags>> =
        repository.taskwithTags.asLiveData()

    fun clearAll() {
        viewModelScope.launch {
            repository.clearAll()
            repository.clearAllTag()
        }
    }

    fun delete(taskWithTags: TaskWithTags) {
        viewModelScope.launch {
            repository.delete(taskWithTags.task)
            repository.tagDelete()
        }
    }

    fun searchtask(str: String): Flow<List<TaskWithTags>> {
        val searchtxt = "$str%"
        return taskDao.search(searchtxt)
    }
}