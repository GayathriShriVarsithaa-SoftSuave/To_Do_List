package com.example.todolist.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class TagsWithTask(
    @Embedded val tags: Tag,
    @Relation(
        parentColumn = "tagId",
        entityColumn = "entryId",
        associateBy = Junction(ToDoListTable::class)
    )
    val tasks: List<Task>
)