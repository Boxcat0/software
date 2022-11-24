package com.example.demo.gymMaster

import com.example.demo.account.Account
import com.example.demo.account.AccountRole
import com.example.demo.account.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class permitController(@Autowired val service: gymAccountService,
                       @Autowired val accountService : AccountService
) {

    @GetMapping("/gymMasterRegist")
    fun userToGym(model: Model):String{
        val gymList:List<gymAccount> = service.findGymAccount()
        model.addAttribute("re",gymList)
        return "permit"
    }

    @PostMapping("/gymMasterRegist")
    fun userToGym2(gymAccount: gymAccount,model: Model
    ):String{
        val permitAccount :gymAccount =service.searchAccountByNumber(gymAccount.number_gymaccount,service.findGymAccount())
        if(permitAccount.id == "null")
        {
            val gymList:List<gymAccount> = service.findGymAccount()
            model.addAttribute("re",gymList)
            return "permit"
        }
        else
        {
            val password : String = permitAccount.pw
            val addAccount = Account(null,permitAccount.id,password,mutableSetOf(AccountRole.GYM))
            if(accountService.searchAccount(addAccount.id,accountService.findAccount()).id!= "null")
            {
                val gymList:List<gymAccount> = service.findGymAccount()
                model.addAttribute("re",gymList)
                return "permit"
            }
            else
            {
                accountService.saveAccount(addAccount)
                return "home"
            }
        }
    }

}