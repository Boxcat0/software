package com.example.demo.account

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Slf4j
@Controller
class AccountRegisterController(
        @Autowired val accountService: AccountService,
        @Autowired val accountRepository: AccountRepository
        )
{
    @GetMapping("/member_sign2")
    fun process():String{
        return "member_sign2"
    }
    private val log = LoggerFactory.getLogger("Controller2")
    @PostMapping("/member_sign2")//회원가입 로직
    fun processForm(account: Account):String{
        val all:List<Account> = accountService.findAccount()
        val target : Account = accountService.searchAccount(account.id, all)
        return if(target.id == "null")
        {
            log.info("account : $account")
            val insertMember = accountService.saveAccount(account)
            log.info("inserted member:$insertMember")
            "home"
        } else {
            "member_sign2"
        }
    }

}