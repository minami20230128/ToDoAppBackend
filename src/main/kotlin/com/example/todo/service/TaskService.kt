package com.example.todo.service

import com.example.todo.entity.Task
import com.example.todo.input.TaskInput
import com.example.todo.input.TaskStatusInput
import com.example.todo.repository.TaskRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TaskService(
    private val taskRepository: TaskRepository
) {
    fun findAll(): Array<Task> {
        return taskRepository.findAllTasks();
    }

    fun findById(id: Long): Task? {
        return taskRepository.findTaskById(id)
    }

    fun insert(taskInput: TaskInput): Task {
        val task = Task(
            title = taskInput.title,
            startDate = taskInput.startDate,
            dueDate = taskInput.dueDate,
            taskCondition = taskInput.taskCondition,
            memo = taskInput.memo,
            status = taskInput.status,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        return taskRepository.insertTask(task);
    }

    fun updateStatus(id: Long, taskStatusInput: TaskStatusInput): Boolean {
        val updatedCount = taskRepository.updateTaskStatus(id, taskStatusInput.status);
        return updatedCount > 0;
    }

    fun update(id: Long, taskInput: TaskInput): Boolean {
        val task = Task(
            title = taskInput.title,
            startDate = taskInput.startDate,
            dueDate = taskInput.dueDate,
            taskCondition = taskInput.taskCondition,
            memo = taskInput.memo,
            status = taskInput.status,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        val updatedCount = taskRepository.updateTask(id, task);
        return updatedCount > 0;
    }

    fun delete(id: Long): Boolean{
        val deletedCount = taskRepository.deleteTask(id);
        return deletedCount > 0;
    }
}