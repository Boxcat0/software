package com.example.demo.reviewer

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class ReviewController(val service: ReviewService) {
    @GetMapping("/allreview")
    fun review(@AuthenticationPrincipal userDetails: UserDetails, model: Model): String{
        model.addAttribute("re", service.findReviews())
        model.addAttribute("name",userDetails.username)
        return "reviewer"
    }
    @PostMapping("/allreview")
    fun  reviewposting(@RequestBody review: Review)
    {
        service.reviewpost(review)
    }
}