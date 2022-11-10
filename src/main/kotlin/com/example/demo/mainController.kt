package com.example.demo

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import javax.servlet.http.HttpSession


@Controller
class mainController {
    @GetMapping("/")
    fun welcome(@AuthenticationPrincipal userDetails: UserDetails, model : Model,session: HttpSession): String {
        val name: String = userDetails.username
        println(name)
        model.addAttribute("userName", name)
        session.removeAttribute("sessionGym")
        session.removeAttribute("sessionPosition")
        return "home"
    }

    @GetMapping("/home")
    fun welcom2(@AuthenticationPrincipal userDetails: UserDetails, session: HttpSession,model : Model):String {
        val name: String = userDetails.username
        println(name)
        session.getAttribute("user")
        model.addAttribute("userName",name)
        return "home"
    }


}