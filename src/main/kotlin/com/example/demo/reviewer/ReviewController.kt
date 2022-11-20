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
class ReviewController(val service: ReviewService,
                       val repository: ReviewRepository
) {
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
    @RequestMapping("/find_review")
    fun reviewFinding(review : Review,
                      @AuthenticationPrincipal userDetails: UserDetails,
                      model: Model
    ):String
    {
        model.addAttribute("re", service.findGymByStar(review.star))
        model.addAttribute("name",userDetails.username)
        return "reviewer"
    }
    @PostMapping("/find_word_review")
    fun reviewWordFinding(review: Review,
                          @AuthenticationPrincipal userDetails: UserDetails,
                          model: Model
    ):String
    {
        val targetword : String = review.reviews
        model.addAttribute("re",service.findReviewByWord(targetword))
        model.addAttribute("name",userDetails.username)
        return "reviewer"
    }
}