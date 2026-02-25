package com.example.todolist.add_task

import android.app.Application
import androidx.lifecycle.*
import com.example.todolist.data.AppDatabase
import com.example.todolist.data.Tag
import com.example.todolist.data.Task
//import com.example.todolist.data.TaskRepository
import com.example.todolist.data.ToDoListTable
import kotlinx.coroutines.launch

class AddViewModel(application: Application) : AndroidViewModel(application) {

    private val todolistdao = AppDatabase.getDatabase(application).toDoListDao()
    private val taskdao = AppDatabase.getDatabase(application).taskDao()
    private val tagdao = AppDatabase.getDatabase(application).tagDao()

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> = _message

    private val _taskAdded = MutableLiveData<Boolean>()
    val taskAdded: LiveData<Boolean> = _taskAdded

    fun addTaskWithTags(title: String, tags: List<Tag>) {
        viewModelScope.launch {
            try {
                val taskId = taskdao.insert(Task(title = title))
                val tagIds = tags.map { tag ->
                    val existingTag = tagdao.getTagByName(tag.tag)
                    if (existingTag != null) {
                        existingTag.tagId
                    } else {
                        tagdao.insertTag(tag)
                    }
                }
                tagIds.forEach { tagId ->
                    todolistdao.insert(ToDoListTable(entryId = taskId, tagId = tagId))
                }
                _taskAdded.value = true
            } catch (e: Exception) {
                _message.value = "Failed to add task: ${e.message}"
            }
        }
    }

    fun resetTaskAdded() {
        _taskAdded.value = false
    }
}