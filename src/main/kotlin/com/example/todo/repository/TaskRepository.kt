package com.example.todo.repository

import com.example.todo.entity.Status
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

    fun findTaskById(id: Long): Task {
        return taskMapper.findTaskById(id)
    }

    fun insertTask(task: Task): Task {
        taskMapper.insertTask(task);
        return task;
    }

    fun updateTaskStatus(id: Long, status: Status): Int {
        return taskMapper.updateTaskStatus(id, status.code)
    }

    fun updateTask(id: Long, task: Task): Int {
        return taskMapper.updateTask(id, task)
    }

    fun deleteTask(id: Long): Int {
        return taskMapper.deleteTask(id)
    }
}
