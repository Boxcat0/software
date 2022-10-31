package com.example.demo

import com.example.demo.memberfind.Member
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.RequestDispatcher
import javax.servlet.http.HttpServletRequest


@Controller
class mainController {
    @GetMapping("/")
    fun welcome(): String{
        return "home"
    }

    @GetMapping("/register")
    fun regit(): String{
        return "register"
    }
}