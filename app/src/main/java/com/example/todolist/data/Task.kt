package com.example.todolist.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "entries")
data class Task (
    @PrimaryKey(autoGenerate = true)
    val entryId:Int=0,
    val title:String,
)
@Entity(tableName = "tags")
data class Tag(
    @PrimaryKey(autoGenerate = true)
    val tagId:Int=0,
    val tag:String
)
@Entity(tableName = "to_do_list_table",
    //composite primary key(pairing will not be repeated)
    primaryKeys = ["entryId","tagId"],
    foreignKeys = [
        ForeignKey(
            entity = Task::class,
            parentColumns = ["entryId"],
            childColumns = ["entryId"],
            onDelete = ForeignKey.CASCADE
        ),
    ForeignKey(
        entity = Tag::class,
        parentColumns = ["tagId"],
        childColumns = ["tagId"],
        onDelete = ForeignKey.CASCADE
    )
    ])
data class ToDoListTable(
    val entryId: Int,
    val tagId: Int
)
