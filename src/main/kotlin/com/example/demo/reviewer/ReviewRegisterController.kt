package com.example.demo.reviewer

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import javax.servlet.http.HttpSession


@Controller
class ReviewRegisterController( val service: ReviewService) {

    @GetMapping("/board")
    fun showmap(@AuthenticationPrincipal userDetails: UserDetails, model : Model):String {
        model.addAttribute("id",userDetails.username)
        return "board"
    }

    private val log = LoggerFactory.getLogger("ReviewRegisterController2")
    @PostMapping("/board")//리뷰 저장
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