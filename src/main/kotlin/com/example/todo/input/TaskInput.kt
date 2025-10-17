package com.example.todo.input

import java.time.LocalDate
import java.time.LocalDateTime
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class TaskInput (
    val id: Long? = null,

    @field:NotBlank(message = "タイトルは必須です")
    val title: String,

    @field:NotNull(message = "ステータスは必須です")
    val startDate: LocalDate,

    @field:NotNull(message = "ステータスは必須です")
    val dueDate: LocalDate,

    @field:Size(max = 100, message = "完了条件は100文字以内で入力してください")
    val taskCondition: String,

    @field:Size(max = 500, message = "メモは500文字以内で入力してください")
    val memo: String,

    @field:NotNull(message = "ステータスは必須です")
    val status: Int,

    val createdAt: LocalDateTime? = null,

    val updatedAt: LocalDateTime? = null
)
