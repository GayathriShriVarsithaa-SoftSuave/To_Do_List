package com.example.todolist.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import com.example.todolist.data.AppDatabase
import com.example.todolist.data.TaskRepository

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).taskDao()
    private val repository = TaskRepository(dao)

    fun getTask(id: Int) =
        repository.getTaskById(id).asLiveData()
}