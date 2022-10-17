package com.example.demo

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class mainController {
    @GetMapping("/")
    fun welcome(): String{
        return "home"
    }

}