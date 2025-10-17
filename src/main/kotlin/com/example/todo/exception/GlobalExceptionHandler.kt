package com.example.todo.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException

@RestControllerAdvice
class GlobalExceptionHandler {

    // JSONの構文や型変換エラー
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException): ResponseEntity<Map<String, String>> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(mapOf("message" to "リクエストの形式またはデータ型が不正です"))
    }

    // パスパラメータやクエリパラメータの型変換エラー
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleTypeMismatch(ex: MethodArgumentTypeMismatchException): ResponseEntity<Map<String, String>> {
        val field = ex.name
        val value = ex.value
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(mapOf("message" to "パラメータ '$field' の値 '$value' は正しい形式ではありません"))
    }

    //BeanValidation失敗
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<String> {
        val msg = ex.bindingResult.allErrors.joinToString(", ") { it.defaultMessage ?: "Invalid" }
        return ResponseEntity(msg, HttpStatus.BAD_REQUEST)
    }

    //その他
    @ExceptionHandler(Exception::class)
    fun handleAll(ex: Exception): ResponseEntity<String> {
        return ResponseEntity("サーバーエラーが発生しました", HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
