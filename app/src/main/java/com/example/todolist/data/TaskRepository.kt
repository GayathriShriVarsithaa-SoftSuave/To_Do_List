package com.example.todolist.data

//import androidx.room.Query
import kotlinx.coroutines.flow.Flow

class TaskRepository(
    private val toDoListDao: ToDoListDao
) {
    fun getAllTasks(): Flow<List<Task>> {
        return toDoListDao.getAllTasks()
    }

    suspend fun delete(task: Task) {
        toDoListDao.delete(task)
    }

    suspend fun clearAll() {
        toDoListDao.clearAll()
    }
}