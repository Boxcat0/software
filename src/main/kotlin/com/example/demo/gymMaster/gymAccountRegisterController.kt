package com.example.demo.gymMaster

import com.example.demo.Event.eventGym
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class gymAccountRegisterController(@Autowired val service: gymAccountService) {
    @GetMapping("/gymMaster")
    fun mapclickevent2():String
    {
        return "gymMaster"
    }
    private val log = LoggerFactory.getLogger("Controller2")
    @PostMapping("/gymMaster")
    fun registgymMasterPost(gymaccount:gymAccount):String{
        println(gymaccount)
        val all : List<gymAccount> = service.findGymAccount()
        val target : gymAccount = service.searchAccount(gymaccount.gym, all)
        return if(target.gym == "null") {
            val insert: gymAccount = service.saveAccount(gymaccount)
            log.info("inserted member:$insert")
            "home"
        }else {
            "gymMaster"
        }
    }
}