package com.example.demo.Reservation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/search")
class userReservationControllerSearch(@Autowired val repository: reservationRepository,
                                      @Autowired val service: reservationService
) {
    @PostMapping("/Time")
    fun findRev(@AuthenticationPrincipal userDetails: UserDetails,
                reservation : reservation,
                model: Model,
                session : HttpSession
    ):String{
        val reservationAll : List<reservation> = repository.findBy()
        val reservationAllByTime : List<reservation> = service.findEveryReservationByTime(reservationAll,reservation.times)
        model.addAttribute("userName",userDetails.username)
        model.addAttribute("re",reservationAllByTime)
        return "userReservation"
    }
    @PostMapping("/Gym")
    fun findRevGym(@AuthenticationPrincipal userDetails: UserDetails,
                   reservation: reservation, model: Model, session: HttpSession
    ):String {
        val reservationAll : List<reservation> = repository.findBy()
        val reservationAllByGym : List<reservation> = service.findEveryReservationByGym(reservationAll,reservation.gym)
        model.addAttribute("userName",userDetails.username)
        model.addAttribute("re",reservationAllByGym)
        return "userReservation"
    }
    @PostMapping("/Name")
    fun findRevName(@AuthenticationPrincipal userDetails: UserDetails,
                    reservation: reservation, model: Model, session: HttpSession):String
    {
        val reservationAll : List<reservation> = repository.findBy()
        println(reservation.name)
        val reservationAllByName : List<reservation> = service.findEveryReservationByName(reservationAll,reservation.name)
        model.addAttribute("userName",userDetails.username)
        model.addAttribute("re",reservationAllByName)
        return "userReservation"
    }
    @PostMapping("/reset")
    fun reset(@AuthenticationPrincipal userDetails: UserDetails,
              model: Model, session: HttpSession
    ):String{
        model.addAttribute("userName", userDetails.username)
        val userReservation : List<reservation> = service.findReservationBy()
        val userReservationFinal : List<reservation> = service.findEveryReservationById(userReservation, userDetails.username)
        model.addAttribute("re",userReservationFinal)
        return "userReservation"
    }
}