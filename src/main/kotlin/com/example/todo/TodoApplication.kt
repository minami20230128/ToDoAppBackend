package com.example.todo

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@MapperScan("com.example.todo.mapper")
class TodoApplication

fun main(args: Array<String>) {
	runApplication<TodoApplication>(*args)
}
