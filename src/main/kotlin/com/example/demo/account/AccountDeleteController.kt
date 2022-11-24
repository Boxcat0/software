package com.example.demo.account

import com.example.demo.gymMaster.gymAccountService
import com.example.demo.reviewer.Review
import com.example.demo.reviewer.ReviewService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import javax.servlet.http.HttpSession


@Controller
class AccountDeleteController(@Autowired val accountService : AccountService,
                              @Autowired val accountRepository: AccountRepository,
                              @Autowired private val pass: PasswordEncoder,
                              @Autowired val gymService: gymAccountService,
                              @Autowired val reviewService: ReviewService
) {
    @GetMapping("/member_delete")
    fun deletestart(): String{
        return "member_delete"
    }

    @PostMapping("/member_delete")
    fun deleteProcess(@AuthenticationPrincipal user: User,
                      account : Account,
                      session: HttpSession,
    ): String{
        val newPassword : String = account.password//받는건 확실함
        val oldPassword : String = user.password//현재 로그인된 사용자
        if(pass.matches(newPassword, oldPassword))//로그인된 사용자와 입력된 비밀번호 비교
        {
            if(gymService.searchAccountById(account.id,gymService.findGymAccount()).id != "null")
            {
                val deleteTarget : Account = accountRepository.findAccountById(user.username)
                accountService.remove(deleteTarget)
                gymService.gymRemove(gymService.searchAccountById(account.id,gymService.findGymAccount()))
                session.invalidate()
                println("Delete Sucess")
            }
            else{
                val targetReview : List<Review> = reviewService.findReviews()
                val targetReviewById:List<Review> = reviewService.findReviewStarById(targetReview,user.username)
                val deleteTarget : Account = accountRepository.findAccountById(user.username)
                accountService.remove(deleteTarget)
                reviewService.changeMemberReview(targetReviewById)
                session.invalidate()
                println("Delete Sucess")
            }
            return "home"
        }
        else{
            println("Delete fails")
            return "redirect:/member_delete"
        }
    }
}