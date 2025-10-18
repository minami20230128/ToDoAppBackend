package com.example.todo.mapper

import com.example.todo.entity.Task
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface TaskMapper {
    fun findAll(): Array<Task>
    fun findTaskById(@Param("id") id: Long): Task?
    fun insertTask(task: Task): Int
    fun updateTaskStatus(@Param("id") id: Long, @Param("status") status: Int): Int
    fun updateTask(@Param("id") id: Long, @Param("task") task: Task): Int
    fun deleteTask(@Param("id") id: Long): Int
}