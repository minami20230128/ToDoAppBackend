package com.example.todo.entity

enum class Status(val code: Int) {
    TODO(0),
    IN_PROGRESS(1),
    DONE(2);

    companion object {
        fun fromCode(code: Int): Status {
            return values().find { it.code == code }
                ?: throw IllegalArgumentException("Unknown Status code: $code")
        }
    }
}
