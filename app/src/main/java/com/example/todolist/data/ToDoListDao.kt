package com.example.todolist.data

import androidx.room.Dao
//import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import androidx.room.Transaction
//import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoListDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(toDoList: ToDoListTable)

}