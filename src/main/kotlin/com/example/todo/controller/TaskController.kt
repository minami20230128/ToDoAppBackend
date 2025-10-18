package com.example.todo.controller

import com.example.todo.entity.Task
import com.example.todo.input.TaskInput
import com.example.todo.input.TaskStatusInput
import com.example.todo.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TaskController(
    private val taskService: TaskService
) {
    @GetMapping("/api/tasks")
    fun getAllTasks(): Array<Task> {
        return taskService.findAll();
    }

    @GetMapping("/api/tasks/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<Task> {
        val task = taskService.findById(id)
        return if (task != null) {
            ResponseEntity.ok(task)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/api/tasks")
    fun insertTask(@Validated @RequestBody taskInput: TaskInput): ResponseEntity<Task> {
        val task = taskService.insert(taskInput)
        return ResponseEntity.status(HttpStatus.CREATED).body(task)
    }

    @PatchMapping("/api/tasks/{id}")
    fun updateTaskStatus(@Validated @RequestBody taskStatusInput: TaskStatusInput,
                         @PathVariable id: Long): ResponseEntity<Void> {
        val updated = taskService.updateStatus(id, taskStatusInput);
        return if (updated) ResponseEntity.noContent().build()
        else ResponseEntity.notFound().build()
    }

    @PutMapping("/api/tasks/{id}")
    fun updateTask(@Validated @RequestBody taskInput: TaskInput,
                   @PathVariable id: Long): ResponseEntity<Void> {
        val updated = taskService.update(id, taskInput);
        return if (updated) ResponseEntity.noContent().build()
        else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/api/tasks/{id}")
    fun deleteTask(@PathVariable id: Long): ResponseEntity<Void>{
        val deleted = taskService.delete(id);
        return if (deleted) ResponseEntity.noContent().build()
        else ResponseEntity.notFound().build()
    }
}