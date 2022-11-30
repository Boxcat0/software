package com.example.demo.gymMaster

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
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
    fun registgymMasterPost(gymaccount:gymAccount,model: Model,@AuthenticationPrincipal userDetails: UserDetails):String{
        println(gymaccount)
        val all : List<gymAccount> = service.findGymAccount()
        val target : gymAccount = service.searchAccount(gymaccount.gym, all)
        return if(target.gym == "null") {
            val insert: gymAccount = service.saveAccount(gymaccount)
            log.info("inserted member:$insert")
            model.addAttribute("userName",userDetails.username)
            return "UserPage.html"
        }else {
            "gymMaster"
        }
    }
}