package com.example.demo.Reservation

import com.example.demo.account.Account
import com.example.demo.account.AccountRole
import com.example.demo.account.AccountService
import com.example.demo.gymMaster.gymAccount
import com.example.demo.gymMaster.gymAccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import javax.servlet.http.HttpSession

@Controller
class masterReservationController(@Autowired val service: reservationService,
                                  @Autowired val repository: reservationRepository,
                                  @Autowired val gymService : gymAccountService,
                                  @Autowired val accountService: AccountService
) {
    @GetMapping("/adminReservationCheck")
    fun checkAdmin(@AuthenticationPrincipal userDetails: UserDetails,
                   session: HttpSession, model: Model):String
    {
        model.addAttribute("userName",userDetails.username)
        val allReservation : List<reservation> = service.findReservationBy()
        model.addAttribute("re",allReservation)
        return "adminReservationCheck"
    }

    @GetMapping("/reservationCheck")
    fun check(@AuthenticationPrincipal userDetails: UserDetails,
              session: HttpSession, model: Model
    ):String{
        val gymAccountAll : List<gymAccount> =gymService.findGymAccount()
        val targetId : String = userDetails.username
        val allAccount : List<Account> = accountService.findAccount()
        val adminAccount : Account = accountService.searchAccount(userDetails.username,allAccount)
        val targetGymAccount : gymAccount = gymService.searchAccountById(targetId,gymAccountAll)
        println(targetGymAccount.roles)
        if(targetGymAccount.roles.contains(AccountRole.GYM)||adminAccount.roles.contains(AccountRole.ADMIN))
        {
            model.addAttribute("userName",userDetails.username)
            model.addAttribute("GymId",targetGymAccount.gym)
            session.setAttribute("GymId",targetGymAccount.gym)
            val gym : String = targetGymAccount.gym
            val reservationAll : List<reservation> = service.findReservationBy()
            val reservationAllByGym : List<reservation> = service.findEveryReservationByGym(reservationAll,gym)
            model.addAttribute("re",reservationAllByGym)
            return "reservationCheck"
        }
        else
        {
            model.addAttribute("userName",userDetails.username)
            return "map_click"
        }
    }
    @PostMapping("/delete_reservation")
    fun deleteRev(@AuthenticationPrincipal userDetails: UserDetails,
                  reservation : reservation,
                  model: Model,
                  session : HttpSession
    ):String
    {

        val reservationAll : List<reservation> = repository.findBy()
        val reservationAllByName : reservation = service. findEveryReservationByNameTime(reservationAll,reservation.name,reservation.times)
        if(reservationAllByName.id =="null")
        {
            model.addAttribute("userName",userDetails.username)
            model.addAttribute("GymId",session.getAttribute("GymId"))
            val gym : String = session.getAttribute("GymId").toString()
            val reservationAllByGym : List<reservation> = service.findEveryReservationByGym(reservationAll,gym)
            model.addAttribute("re",reservationAllByGym)
            return "reservationCheck"
        }
        else{
            model.addAttribute("userName",userDetails.username)
            model.addAttribute("GymId",session.getAttribute("GymId"))
            service.removeReservation(reservationAllByName)
            val gym : String = session.getAttribute("GymId").toString()
            val reservationAllByGym : List<reservation> = service.findEveryReservationByGym(reservationAll,gym)
            model.addAttribute("re",reservationAllByGym)
            return "reservationCheck"
        }
    }

    @PostMapping("/search_reservation")
    fun findRev(@AuthenticationPrincipal userDetails: UserDetails,
                reservation : reservation,
                model: Model,
                session : HttpSession
    ):String{
        val reservationAll : List<reservation> = repository.findBy()
        val reservationAllByTime : List<reservation> = service.findEveryReservationByTime(reservationAll,reservation.times)
        if(reservationAllByTime.isEmpty())
        {
            model.addAttribute("userName",userDetails.username)
            model.addAttribute("GymId",session.getAttribute("GymId"))
            return "reservationCheck"
        }
        else
        {
            model.addAttribute("userName",userDetails.username)
            model.addAttribute("GymId",session.getAttribute("GymId"))
            model.addAttribute("re",reservationAllByTime)
            return "reservationCheck"
        }
    }
}