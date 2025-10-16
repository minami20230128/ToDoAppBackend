package com.example.todo.service

import com.example.todo.entity.Task
import com.example.todo.mapper.TaskMapper
import com.example.todo.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val taskRepository: TaskRepository
) {
    fun findAll(): Array<Task> {
        return taskRepository.findAllTasks();
    }
}