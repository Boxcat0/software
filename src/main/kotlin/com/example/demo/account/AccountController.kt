package com.example.demo.account

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/view")
class AccountController {

    @RequestMapping("/success")//로그인 성공시 화면
    fun success(request: HttpServletRequest): String {
        return "home"
    }
    @GetMapping("/logout")
    fun logoutPage(request: HttpServletRequest): String{
        return "redirect:/login"
    }
}