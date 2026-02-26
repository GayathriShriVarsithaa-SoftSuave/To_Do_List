package com.example.todolist.data

import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(
    tableName = "to_do_list_table",
    //composite primary key(pairing will not be repeated. entryId and tagId can be repeated)
    primaryKeys = ["entryId", "tagId"],
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
    ]
)
data class ToDoListTable(
    val entryId: Long,
    val tagId: Long
)