package com.example.demo.reviewer

import com.example.demo.account.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpSession

@Controller
class UserReview(@Autowired private val pass: PasswordEncoder,
                 @Autowired val reviewRepository: ReviewRepository,){
    @GetMapping("/user_review")
    fun intocheck():String
    {
        return "usercheck"
    }
    @GetMapping("/gym_review")
    fun intocheck2(session: HttpSession, model: Model):String
    {
        model.addAttribute("GymId",session.getAttribute("GymId"))
        return "gymcheck"
    }
    @RequestMapping("/gym_review")
    fun searchReview2(@AuthenticationPrincipal user: UserDetails,
                     session: HttpSession,
                     review: Review,
                     model: Model
    ):String{
        if(session.getAttribute("Switch") == null)
        {
            session.setAttribute("Switch","Gym")
        }
        else
        {
            session.removeAttribute("Switch")
            session.setAttribute("Switch","Gym")
        }
        val targetgym : String? = review.gym
        println(targetgym)
        val searchTarget : List<Review> = reviewRepository.findReviewsByGym(targetgym)//헬스장 기반 리뷰 조회
        println(searchTarget)
        model.addAttribute("name", user.username)
        model.addAttribute("re", searchTarget)
        model.addAttribute("GymId",review.gym)
        return "reviewer"
    }
    @RequestMapping("/user_review")
    fun searchReview(@AuthenticationPrincipal user: User,
                     account : Account,
                     session: HttpSession,
                     review: Review,
                     model: Model
    ):String{
        val newPassword : String = account.password
        val oldPassword : String = user.password
        val targetid : String = account.id
        if(pass.matches(newPassword, oldPassword))//회원 확인
        {
            if(session.getAttribute("Switch") == null)
            {
                session.setAttribute("Switch","User")
            }
            else
            {
                session.removeAttribute("Switch")
                session.setAttribute("Switch","User")
            }
            val searchTarget : List<Review> = reviewRepository.findReviewById(targetid)//회원 아이디 기반 리뷰 조회
            model.addAttribute("name", account.id)
            model.addAttribute("re", searchTarget)
            model.addAttribute("GymId","null")
            session.removeAttribute("GymId")
            return "reviewer"
        }
        return "usercheck"
    }
}