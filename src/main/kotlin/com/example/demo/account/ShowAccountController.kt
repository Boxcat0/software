package com.example.demo.account

import com.example.demo.reviewer.Review
import com.example.demo.reviewer.ReviewService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody


@Controller
class ShowAccountController(val service: AccountService) {

    @GetMapping("/allaccount")
    fun review(model: Model): String{
        model.addAttribute("re", service.findAccount())
        model.addAttribute("name","admin")
        return "Memberselect2"
    }

    @PostMapping("/allaccount")
    fun  accountpost(@RequestBody account : Account)
    {
        service.Accountpost(account)
    }
}