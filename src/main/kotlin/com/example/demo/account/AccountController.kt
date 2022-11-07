package com.example.demo.account

import lombok.RequiredArgsConstructor
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpSession

@RequiredArgsConstructor
@Controller
@RequestMapping("/view")
class AccountController {
    @PostMapping("/success")//로그인 성공시 화면
    fun success(@AuthenticationPrincipal user : User,model : Model,session: HttpSession): String {
        val name : String = user.username
        model.addAttribute("user",user)
        model.addAttribute("userName", name)
        return "home"
    }
}