package com.example.demo.account

import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.security.Principal
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession
import javax.validation.constraints.Null

@RequiredArgsConstructor
@Controller
@RequestMapping("/view")
class AccountController {
    @PostMapping("/success")//로그인 성공시 화면
    fun success(request: HttpServletRequest,model: Model): String {
        model.addAttribute("UserName", "test")
        return "home"
    }
    @GetMapping("/logout")
    fun logoutPage(request: HttpServletRequest): String{
        return "redirect:/login"
    }
}