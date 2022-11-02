package com.example.demo.reviewer

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody


@Controller
class ReviewController(val service: ReviewService) {
    @GetMapping("/allreview")
    fun review(model: Model): String{
        model.addAttribute("re", service.findReviews())
        model.addAttribute("name","admin")
        return "reviewer"
    }
    @PostMapping("/allreview")
    fun  reviewpost(@RequestBody review: Review)
    {
        service.reviewpost(review)
    }
}