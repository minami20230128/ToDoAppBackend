package com.example.todo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class FrontendController {
    @RequestMapping(value = ["/", "/{path:[^\\.]*}"])
    fun forward(): String {
        // すべてのパスを index.html にフォワード
        return "forward:/index.html"
    }
}