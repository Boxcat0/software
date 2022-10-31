package com.example.demo.memberfind

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Slf4j
@Controller
class RegisterController(
        val registerMemberRepository: registerMemberRepository)
{
    @GetMapping("/member_sign")
    fun process():String{
        return "register"
    }
    private val log = LoggerFactory.getLogger("Controller2")
    @PostMapping("/member_sign")//회원가입 로직
    fun processForm(registermember : registerMember, member :Member):String{
        log.info("registermember : $registermember")
        val insertMember = registerMemberRepository.save(registermember)
        log.info("inserted member:$insertMember")
        return "home"
    }
}