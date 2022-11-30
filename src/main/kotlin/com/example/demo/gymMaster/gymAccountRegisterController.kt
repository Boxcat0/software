package com.example.demo.gymMaster

import com.example.demo.account.Account
import com.example.demo.account.AccountService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class gymAccountRegisterController(@Autowired val service: gymAccountService,
                                   @Autowired val accountService: AccountService
) {
    @GetMapping("/gymMaster")
    fun mapclickevent2():String
    {
        return "gymMaster"
    }
    private val log = LoggerFactory.getLogger("Controller2")
    @PostMapping("/gymMaster")
    fun registgymMasterPost(gymaccount:gymAccount,model: Model,@AuthenticationPrincipal userDetails: UserDetails):String{
        val all : List<gymAccount> = service.findGymAccount()
        val target : gymAccount = service.searchAccount(gymaccount.gym, all)
        return if(target.gym == "null") {
            val insert: gymAccount = service.saveAccount(gymaccount)
            log.info("inserted member:$insert")
            model.addAttribute("userName",userDetails.username)
            return "home"
        }else {
            "gymMaster"
        }
    }

    @GetMapping("/allGymMaster")
    fun gymMaster(@AuthenticationPrincipal userDetails: UserDetails, model: Model):String
    {
        val all : List<gymAccount> = service.findGymAccount()
        model.addAttribute("re",all)
        model.addAttribute("userName",userDetails.username)
        return "allGymMaster"
    }

    @PostMapping("/admin/delete_Master")
    fun deleteGymMaster(@AuthenticationPrincipal userDetails: UserDetails, gymaccount: gymAccount, model: Model):String
    {
        val targetId : String = gymaccount.id
        val all : List<gymAccount> = service.findGymAccount()
        val target : gymAccount = service.searchAccountById(targetId,all)
        if(target.id =="null")
        {
            model.addAttribute("re2",all)
            model.addAttribute("userName",userDetails.username)
            return "allGymMaster"
        }
        else{
            val targetAccount : Account = accountService.searchAccount(gymaccount.id,accountService.findAccount())
            accountService.remove(targetAccount)
            model.addAttribute("userName",userDetails.username)
            service.gymRemove(target)
            val all2 : List<gymAccount> = service.findGymAccount()
            model.addAttribute("re2",all2)
            return "allGymMaster"
        }
    }
}