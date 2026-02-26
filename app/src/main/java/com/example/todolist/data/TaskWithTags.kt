package com.example.todolist.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class TaskWithTags(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "entryId",
        entityColumn = "tagId",
        associateBy = Junction(ToDoListTable::class)
    )
    val tags: List<Tag>
)