package com.example.demo.account

import com.example.demo.memberfind.Member
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class RegisterController {
    @GetMapping("/register")
    fun registerForm():String{
        return "register"
    }

    @PostMapping("/register")
    fun registerF(member: Member):String{
        return "redirect:/login"
    }
}