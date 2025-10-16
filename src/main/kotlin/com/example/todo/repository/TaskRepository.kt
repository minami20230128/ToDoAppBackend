package com.example.todo.repository

import com.example.todo.entity.Task
import com.example.todo.mapper.TaskMapper
import org.springframework.stereotype.Repository

@Repository
class TaskRepository(
    private val taskMapper: TaskMapper
) {

    fun findAllTasks(): Array<Task> {
        return taskMapper.findAll()
    }

    fun insertTask(task: Task): Long {
        return taskMapper.insertTask()
    }

    fun updateTaskStatus(id: Int, task: Task): Long {
        return taskMapper.updateTaskStatus(id, task)
    }

    fun updateTask(id: Int, task: Task): Long {
        return taskMapper.updateTask(id, task)
    }

    fun deleteTask(id: Long): Long {
        return taskMapper.deleteTask(id)
    }
}
