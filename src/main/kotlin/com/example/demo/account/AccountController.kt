package com.example.demo.account

import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
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
class AccountController(@Autowired val AccountService : AccountService) {
    @PostMapping("/success")//로그인 성공시 화면
    fun success(@AuthenticationPrincipal user : User,model : Model,session: HttpSession): String {
        val name: String = user.username
        val target : Account = AccountService.findRoleAccount(user.username)
        println(target)
        model.addAttribute("AccountRole",target.roles)
        model.addAttribute("userName", name)
        session.removeAttribute("GymId")
        session.removeAttribute("GymPosition")
        return "home"
    }
}