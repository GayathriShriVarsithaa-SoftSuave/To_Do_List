package com.example.todolist.data

import androidx.room.Dao
import androidx.room.Delete
//import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

//import androidx.room.Transaction
//import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {
    @Insert
    suspend fun insertTag(tag: Tag): Long

    @Query("SELECT * FROM tags WHERE tag= :tagname LIMIT 1")
    suspend fun getTagByName(tagname: String): Tag?

    @Query("DELETE FROM tags")
    suspend fun clearAll()

    @Query("DELETE FROM tags WHERE tagId NOT IN(SELECT DISTINCT tagId FROM to_do_list_table)")
    suspend fun tagdelete()
}