package com.example.demo.account

import lombok.RequiredArgsConstructor
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequiredArgsConstructor
@Controller
@RequestMapping("/view")
class AccountController {
    @PostMapping("/success")//로그인 성공시 화면
    fun success(): String {
        return "home"
    }
}