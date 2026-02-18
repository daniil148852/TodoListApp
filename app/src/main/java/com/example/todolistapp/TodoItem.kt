package com.example.todolistapp

data class TodoItem(
    val id: Long = System.currentTimeMillis(),
    var text: String,
    var isCompleted: Boolean = false
)
