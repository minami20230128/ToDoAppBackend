package com.example.todo.entity

import java.time.LocalDate
import java.time.LocalDateTime

data class Task(
    val id: Long? = null,
    val title: String,
    val startDate: LocalDate,
    val dueDate: LocalDate,
    val taskCondition: String,
    val memo: String,
    val status: Int,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)