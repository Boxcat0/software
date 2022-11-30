package com.example.demo

import com.example.demo.account.Account
import com.example.demo.account.AccountService
import com.example.demo.gymMaster.gymAccount
import com.example.demo.gymMaster.gymAccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpSession


@Controller
class mainController(@Autowired val gymAccountService: gymAccountService,
                     @Autowired val AccountService : AccountService
) {
    @GetMapping("/")
    fun welcome(@AuthenticationPrincipal userDetails: UserDetails, model : Model,session: HttpSession): String {
        val name: String = userDetails.username
        val target : Account = AccountService.findRoleAccount(userDetails.username)
        println(target)
        model.addAttribute("AccountRole",target.roles)
        model.addAttribute("userName", name)
        session.removeAttribute("GymId")
        session.removeAttribute("GymPosition")
        return "home"
    }
    @GetMapping("/myPage")
    fun myPage(@AuthenticationPrincipal userDetails: UserDetails,
               model: Model):String{
        model.addAttribute("userName",userDetails.username)
        return "UserPage.html"
    }
    @GetMapping("/adminPage")
    fun adminPage(@AuthenticationPrincipal userDetails: UserDetails,model: Model):String{
        model.addAttribute("userName", userDetails.username)
        return "adminPage"
    }
    @GetMapping("/registerPage")
    fun registPage(@AuthenticationPrincipal userDetails: UserDetails,model: Model):String{
        model.addAttribute("userName", userDetails.username)
        return "registerPage.html"
    }
    @GetMapping("/gymMasterPage")
    fun gymMasterPage(@AuthenticationPrincipal userDetails: UserDetails,model: Model):String{
        model.addAttribute("userName", userDetails.username)
        val gym:List<gymAccount> = gymAccountService.findGymAccount()
        val targetGymAccount : gymAccount = gymAccountService.searchAccountById(userDetails.username,gym)
        if(targetGymAccount.id !="null")
        {
            model.addAttribute("GymId", targetGymAccount.gym)
            return "gymMasterPage"
        }
        else
        {
            if(userDetails.username =="admin12")
            {
                return "adminPage"
            }
            return "redirect:/"
        }
    }
    @GetMapping("/home")
    fun welcom2(@AuthenticationPrincipal userDetails: UserDetails, session: HttpSession,model : Model):String {
        val name: String = userDetails.username
        val target : Account = AccountService.findRoleAccount(userDetails.username)
        model.addAttribute("AccountRole",target.roles)
        model.addAttribute("userName",name)
        return "home"
    }
}