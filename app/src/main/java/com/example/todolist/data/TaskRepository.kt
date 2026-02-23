package com.example.todolist.data

//import androidx.room.Query
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao){
    val allTasks: Flow<List<Task>> = taskDao.getAllTasks()
    suspend fun insert(task:Task){
        taskDao.insert(task)
    }
    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }
    fun getTaskById(id: Int): Flow<Task> {
        return taskDao.getTaskById(id)
    }
    suspend fun clearAll() {
        taskDao.deleteAllTasks()
    }

}