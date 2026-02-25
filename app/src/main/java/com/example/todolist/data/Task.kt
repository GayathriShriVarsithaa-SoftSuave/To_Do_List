package com.example.todolist.data

//import androidx.room.Embedded
import androidx.room.Entity
//import androidx.room.ForeignKey
//import androidx.room.Junction
import androidx.room.PrimaryKey
//import androidx.room.Relation

@Entity(tableName = "entries")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val entryId: Long = 0,
    val title: String
)

