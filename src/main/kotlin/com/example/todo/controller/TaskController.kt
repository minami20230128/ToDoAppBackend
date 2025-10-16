package com.example.todo.controller

import com.example.todo.entity.Task
import com.example.todo.repository.TaskRepository
import com.example.todo.service.TaskService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.service.annotation.GetExchange

@RestController
class TaskController(
    private val taskService: TaskService
) {
    @GetMapping("/api/tasks")
    @ResponseBody
    fun getAllTasks(): Array<Task> {
        return taskService.findAll();
    }
}