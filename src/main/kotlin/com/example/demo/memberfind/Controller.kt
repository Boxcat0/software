package com.example.demo.memberfind

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping


@Controller
class Controller(val service: MemberService) {
    @RequestMapping("/allusers")
    fun Mem(model: Model): String{
        model.addAttribute("re", service.findMember())
        return "Memberselect"
    }
    @PostMapping("/allusers")
    fun post(@RequestBody member: Member){
        service.post(member)
    }
}

