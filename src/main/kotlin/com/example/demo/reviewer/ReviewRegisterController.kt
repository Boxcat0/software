package com.example.demo.reviewer

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpSession

@RequestMapping("/{GymId}")
@Controller
class ReviewRegisterController( val service: ReviewService) {

    @GetMapping("/board")
    fun showmap(@AuthenticationPrincipal userDetails: UserDetails, model : Model,session: HttpSession):String {
        model.addAttribute("id",userDetails.username)
        model.addAttribute("gym",session.getAttribute("sessionGym"))
        println(session.getAttribute("sessionGym"))
        return "Starreview"
    }

    private val log = LoggerFactory.getLogger("ReviewRegisterController2")
    @PostMapping("/starsave")//리뷰 저장
    fun makereview(@AuthenticationPrincipal userDetails: UserDetails,review : Review, session: HttpSession):String{
        log.info("review : $review")
        println(review.id)
        println(review.gym)
        println(review.star)
        println(review.reviews)
        val insertReview = service.SaveReview(review)
        log.info("inserted review:$insertReview")
        println(userDetails)
        session.setAttribute("user",userDetails)
        return "redirect:/"
    }
}