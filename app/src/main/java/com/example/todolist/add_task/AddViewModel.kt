package com.example.todolist.add_task

import android.app.Application
import androidx.lifecycle.*
import com.example.todolist.data.AppDatabase
import com.example.todolist.data.Task
import com.example.todolist.data.TaskRepository
import kotlinx.coroutines.launch

class AddViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).taskDao()
    private val repository = TaskRepository(dao)

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> = _message

    private val _taskAdded = MutableLiveData<Boolean>()
    val taskAdded: LiveData<Boolean> = _taskAdded

    fun addTask(title: String, tags: String, description: String) {
        if (title.isEmpty()) {
            _message.value = "Title should not be empty"
            return
        }

        val task = Task(id = 0, title = title, tags = tags, description = description)
        viewModelScope.launch {
            repository.insert(task)
            _taskAdded.postValue(true)
        }
    }

    fun resetTaskAdded() {
        _taskAdded.value = false
    }
}