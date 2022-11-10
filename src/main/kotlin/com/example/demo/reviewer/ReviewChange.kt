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
import javax.servlet.http.HttpSession

@Controller
class ReviewChange(@Autowired private val pass: PasswordEncoder,
                   @Autowired val reviewService: ReviewService,
                   @Autowired val reviewRepository: ReviewRepository
                   ) {
    @GetMapping("/change_review")
    fun intochange(@AuthenticationPrincipal user: UserDetails,model : Model): String{
        model.addAttribute("id", user.username)
        return "change"
    }

    @PostMapping("/change_review")
    fun changeReview(@AuthenticationPrincipal user: User,
                     account : Account,
                     session: HttpSession,
                     review: Review
    ): String{
        val dbPassword = user.password
        println(dbPassword)
        val targetId = account.id
        println(targetId)
        val targetGym = review.gym
        val change = review.reviews
        val targetPassword = account.password
        println(targetPassword)
        if(pass.matches(dbPassword, targetPassword))
        {
            val deleteTargetid : List<Review> = reviewRepository.findReviewById(targetId)//아이디 기준으로 리뷰 조회
            println(deleteTargetid)
            val finaltarget : Review = reviewService.findGymByid(deleteTargetid,targetGym)//아이디랑 헬스장 번호로 조회
            reviewService.changing(finaltarget, change)
            return "redirect:/"
        }
        return "change"
    }
}