package com.example.demo.gymMaster

import com.example.demo.account.Account
import com.example.demo.account.AccountRole
import com.example.demo.account.AccountSave2
import com.example.demo.account.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
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
    @PostMapping("/admin/de_Master")
    fun denied(model: Model, @AuthenticationPrincipal userDetails: UserDetails, gymAccount: gymAccount):String{
        val deleteAccount : gymAccount = service.searchAccountByNumber(gymAccount.number_gymaccount, service.findGymAccount())
        if(deleteAccount.id =="null")
        {
            val gymList:List<gymAccount> = service.findGymAccount()
            model.addAttribute("re",gymList)
            return "permit"
        }
        else
        {
            service.gymRemove(deleteAccount)
            val deleteAccout : Account = accountService.searchAccount(deleteAccount.id,accountService.findAccount())
            accountService.remove(deleteAccout)
            val deleteAccountSave2 : AccountSave2 = accountService.findAccountSaveById(accountService.findAccountSave(),deleteAccount.id)
            accountService.remove2(deleteAccountSave2)
            val gymList:List<gymAccount> = service.findGymAccount()
            model.addAttribute("re",gymList)
            return "permit"
        }
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
            val addAccountSave = AccountSave2(null,permitAccount.id,password)
            if(accountService.searchAccount(addAccount.id,accountService.findAccount()).id!= "null")
            {
                val gymList:List<gymAccount> = service.findGymAccount()
                model.addAttribute("re",gymList)
                return "permit"
            }
            else
            {
                accountService.saveAccount(addAccount)
                accountService.saveAccount2(addAccountSave)
                return "home"
            }
        }
    }

}