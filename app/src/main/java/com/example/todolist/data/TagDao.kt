package com.example.todolist.data

import androidx.room.Dao
//import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
//import androidx.room.Transaction
//import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {
    @Insert
    suspend fun insertTag(tag: Tag):Long
    @Query("SELECT * FROM tags WHERE tag= :tagname LIMIT 1")
    suspend fun getTagByName(tagname:String):Tag?
}