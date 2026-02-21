package com.example.todolist.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task:Task)
    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAllTasks(): Flow<List<Task>>
    @Query("SELECT * FROM tasks WHERE title LIKE '%' || :query || '%' OR tags like '%' || :query || '%'")
    fun searchTasks(query:String):Flow<List<Task>>
    @Query("DELETE FROM Tasks")
    suspend fun deleteAllTasks()
}