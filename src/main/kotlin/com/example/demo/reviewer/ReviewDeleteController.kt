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
class ReviewDeleteController(@Autowired private val pass: PasswordEncoder,
                             @Autowired val reviewService: ReviewService,
                             @Autowired val reviewRepository: ReviewRepository,
) {
    @GetMapping("/adminReviewDelete")
    fun adminDelete(@AuthenticationPrincipal userDetails: UserDetails,model: Model):String{
        val allReview :List<Review> = reviewService.findReviews()
        model.addAttribute("re",allReview)
        model.addAttribute("userName",userDetails.username)
        return "adminReviewDelete"
    }

    @PostMapping("/adminReviewDelete")
    fun adminDeleteReview(@AuthenticationPrincipal userDetails: UserDetails,model: Model,review: Review):String
    {
        val allReview:List<Review> = reviewService.findReviews()
        val targetReview : Review = reviewService.findReviewByNumber(allReview, review.number_review)
        println(targetReview)
        if(targetReview.id !="null")
        {
            reviewService.removing(targetReview)
            model.addAttribute("userName",userDetails.username)
            val allReview2:List<Review> = reviewService.findReviews()
            model.addAttribute("re",allReview2)
            return "adminReviewDelete"
        }
        else
        {
           if(targetReview.reviews !="")
           {
               reviewService.removing(targetReview)
               model.addAttribute("userName",userDetails.username)
               val allReview2:List<Review> = reviewService.findReviews()
               model.addAttribute("re",allReview2)
               return "adminReviewDelete"
           }
            model.addAttribute("re",allReview)
            model.addAttribute("userName",userDetails.username)
            return "adminReviewDelete"
        }
    }
    @GetMapping("/review_delete")
    fun intodelete(model: Model, session: HttpSession): String{
        model.addAttribute("gym",session.getAttribute("GymId"))
        return "review_delete"
    }
    @PostMapping("/review_delete")
    fun delete_review_Process(@AuthenticationPrincipal user: User,
                      account : Account,
                      session: HttpSession, 
                              review: Review
    ): String{
        val newPassword : String = account.password
        val oldPassword : String = user.password
        val targetid : String = account.id
        val targetgym : String? = review.gym
        if(pass.matches(newPassword, oldPassword))//?????? ??????
        {
            val deleteTargetid : List<Review> = reviewRepository.findReviewById(targetid)//????????? ???????????? ?????? ??????
            val finaltarget : Review = reviewService.findGymByid(deleteTargetid,targetgym)//???????????? ????????? ???????????? ????????? ????????? ??????
            reviewService.removing(finaltarget)//??????
            println("Delete Sucess")
            return "redirect:/"
        }
        else{
            println("Delete fails")
            return "redirect:/member_delete"
        }
    }
}