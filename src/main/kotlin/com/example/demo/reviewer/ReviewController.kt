package com.example.demo.reviewer

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpSession

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
                      model: Model,session: HttpSession
    ):String
    {
        val target3 : List<Review> = service.findGymByStar(review.star)
        println(session.getAttribute("Switch"))
        if(session.getAttribute("Switch") == "Gym")
        {
            println("this is Gym")
            val gymForReview : List<Review> = service.findReviewStarByGym(target3,review.gym)
            model.addAttribute("re",gymForReview)
        }
        else
        {
            println("this is user")
            model.addAttribute("re",service.findReviewStarById(target3,review.id))
        }
        model.addAttribute("name",userDetails.username)
        model.addAttribute("GymId",review.gym)
        return "reviewer"
    }
    @PostMapping("/find_word_review")
    fun reviewWordFinding(review: Review,
                          @AuthenticationPrincipal userDetails: UserDetails,
                          model: Model
    ):String
    {
        val targetword : String = review.reviews
        val allReview:List<Review> = service.findReviews()
        val targetGym = review.gym.toString()
        println(targetGym)
        val allReviewById : List<Review> = service.findReviewStarByGym(allReview,targetGym)
        println(allReviewById)
        model.addAttribute("re",service.findReviewByWord(allReviewById,targetword))
        model.addAttribute("name",userDetails.username)
        model.addAttribute("GymId",targetGym)
        return "reviewer"
    }
}