package com.example.demo.memberfind

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping


@Controller
class Controller(
        val service: MemberService,
) {
    @RequestMapping("/allusers")
    fun Mem(model: Model): String{
        model.addAttribute("re", service.findMember())
        model.addAttribute("name","admin")
        return "Memberselect"
    }
    @PostMapping("/allusers")
    fun post(@RequestBody member: Member){
        service.post(member)
    }

}

