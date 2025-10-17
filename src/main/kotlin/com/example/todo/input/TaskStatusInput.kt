package com.example.todo.input

import com.example.todo.entity.Status
import jakarta.validation.constraints.NotNull

data class TaskStatusInput(
    @field:NotNull(message = "ステータスは必須です")
    val status: Status
)
