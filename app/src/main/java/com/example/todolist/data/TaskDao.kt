package com.example.todolist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
//import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM entries ORDER BY entryId DESC")
    fun getAllTasks(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task): Long

    @Delete
    suspend fun delete(task: Task)

    @Query("DELETE FROM entries")
    suspend fun clearAll()

    @Transaction
    @Query("SELECT * FROM entries ORDER BY entryId DESC")
    fun getTaskswithTags(): Flow<List<TaskWithTags>>

    @Transaction
    @Query(
        """SELECT DISTINCT entries.* FROM entries 
        LEFT JOIN to_do_list_table ON entries.entryId=to_do_list_table.entryId 
LEFT JOIN tags ON tags.tagId=to_do_list_table.tagId 
WHERE entries.title LIKE :searchtext OR tags.tag LIKE :searchtext 
ORDER BY entryId DESC"""
    )
    fun search(searchtext: String): Flow<List<TaskWithTags>>
}