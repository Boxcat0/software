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
        val gymId  = session.getAttribute("GymId")
        model.addAttribute("GymId",gymId)
        if(gymId == null)//마이페이지에서 넘어온거
        {
            val deleteTargetid : List<Review> = reviewRepository.findReviewById(user.username)
            model.addAttribute("re",deleteTargetid)
        }
        else
        {
            val deleteTargetid : List<Review> = reviewService.findReviewStarByGym(reviewRepository.findReviewById(user.username),gymId.toString())
            model.addAttribute("re",deleteTargetid)
        }
        return "change"
    }

    @PostMapping("/change_review")
    fun changeReview(@AuthenticationPrincipal user: User,
                     account : Account,
                     session: HttpSession,
                     review: Review,model : Model
    ): String{
        val dbPassword : String = user.password
        val targetId : String = account.id
        val targetNumber :Long? = review.number_review
        val changereviews : String = review.reviews
        val targetPassword : String = account.password
        val deleteTargetid : List<Review> = reviewRepository.findReviewById(user.username)
        if(pass.matches(targetPassword, dbPassword))//확인하고 싶은 비밀번호, 확인 타겟 위치 기억할것
        {
            val deleteTargetid : List<Review> = reviewRepository.findReviewById(targetId)//아이디 기준으로 리뷰 조회
            val finaltarget : Review = reviewService.findReviewByNumber(deleteTargetid,targetNumber)//아이디랑 헬스장 번호로 조회
            if(finaltarget.id =="null")
            {
                model.addAttribute("id", user.username)
                model.addAttribute("GymId",session.getAttribute("GymId"))
                return "change"
            }
            else{
                finaltarget.reviews = changereviews//타겟된 리뷰를 change로 변경
                reviewService.SaveReview(finaltarget)
                return "redirect:/change_review"
            }
        }
        model.addAttribute("id", user.username)
        model.addAttribute("GymId",session.getAttribute("GymId"))
        model.addAttribute("re",deleteTargetid)
        return "change"
    }
}