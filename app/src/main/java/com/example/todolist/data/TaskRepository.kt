package com.example.todolist.data

import androidx.room.Query
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao){
    val allTasks: Flow<List<Task>> = taskDao.getAllTasks()
    suspend fun insert(task:Task){
        taskDao.insert(task)
    }
    fun searchTasks(query: String):Flow<List<Task>>{
        return taskDao.searchTasks(query)
    }
}