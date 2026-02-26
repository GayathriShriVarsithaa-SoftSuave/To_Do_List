package com.example.todolist.data

//import androidx.room.Query
import kotlinx.coroutines.flow.Flow

class TaskRepository(
    private val taskDao: TaskDao, private val tagDao: TagDao
) {
    val taskwithTags: Flow<List<TaskWithTags>> = taskDao.getTaskswithTags()

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }

    suspend fun clearAll() {
        taskDao.clearAll()
    }

    suspend fun clearAllTag() {
        tagDao.clearAll()
    }

    suspend fun tagDelete() {
        tagDao.tagdelete()
    }
}