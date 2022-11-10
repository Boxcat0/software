package com.example.demo.reviewer

import com.example.demo.account.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpSession

@RequestMapping("/{GymId}")
@Controller
class ReviewDeleteController(@Autowired private val pass: PasswordEncoder,
                             @Autowired val reviewService: ReviewService,
                             @Autowired val reviewRepository: ReviewRepository,
) {

    @GetMapping("/review_delete")
    fun intodelete(): String{
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
        if(pass.matches(newPassword, oldPassword))//회원 확인
        {
            val deleteTargetid : List<Review> = reviewRepository.findReviewById(targetid)//아이디 기준으로 리뷰 조회
            println(deleteTargetid)
            val finaltarget : Review = reviewService.findGymByid(deleteTargetid,targetgym)//아이디로 조회한 헬스장을 헬스장 번호로 조회
            reviewService.removing(finaltarget)//삭제
            println("Delete Sucess")
            return "redirect:/"
        }
        else{
            println("Delete fails")
            return "redirect:/member_delete"
        }
    }
}