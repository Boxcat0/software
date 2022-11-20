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
    fun intochange(@AuthenticationPrincipal user: UserDetails,model : Model, session: HttpSession
                   ): String{
        model.addAttribute("id", user.username)
        model.addAttribute("GymId",session.getAttribute("GymId"))
        return "change"
    }

    @PostMapping("/change_review")
    fun changeReview(@AuthenticationPrincipal user: User,
                     account : Account,
                     session: HttpSession,
                     review: Review
    ): String{
        val dbPassword : String = user.password
        println(dbPassword)
        val targetId : String = account.id
        println(targetId)
        val targetGym : String? = review.gym
        val changereviews : String = review.reviews
        val targetPassword : String = account.password
        println(targetPassword)
        if(pass.matches(targetPassword, dbPassword))//확인하고 싶은 비밀번호, 확인 타겟 위치 기억할것
        {
            val deleteTargetid : List<Review> = reviewRepository.findReviewById(targetId)//아이디 기준으로 리뷰 조회
            println(deleteTargetid)
            val finaltarget : Review = reviewService.findGymByid(deleteTargetid,targetGym)//아이디랑 헬스장 번호로 조회
            finaltarget.reviews = changereviews//타겟된 리뷰를 change로 변경
            reviewService.SaveReview(finaltarget)
            return "redirect:/"
        }
        return "change"
    }
}