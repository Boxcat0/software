package com.example.demo.Reservation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpSession

@Controller
class userReservationController(@Autowired val service: reservationService) {

    @GetMapping("/userReservation")
    fun intoUserRev(@AuthenticationPrincipal userDetails: UserDetails,
                    model: Model, session: HttpSession
                    ):String{
        model.addAttribute("userName", userDetails.username)
        val userReservation : List<reservation> = service.findReservationBy()
        val userReservationFinal : List<reservation> = service.findEveryReservationById(userReservation, userDetails.username)
        model.addAttribute("re",userReservationFinal)
        return "userReservation"
    }
    @PostMapping("/delete_reservation2")
    fun deleteRev(reservation: reservation,
                  model: Model,
                  session: HttpSession,
                  @AuthenticationPrincipal userDetails: UserDetails
    ):String{
       val reservationAll : List<reservation> = service.findReservationBy()
        val searchReservationByNameTime : reservation = service.findEveryReservationByNameTime(reservationAll,reservation.name,reservation.times,reservation.gym)
        if(searchReservationByNameTime.id == "null")
        {
            model.addAttribute("userName",userDetails.username)
            val reservationAll2 : List<reservation> = service.findReservationBy()
            val reservationAllByUser : List<reservation> = service.findEveryReservationById(reservationAll2,userDetails.username)
            model.addAttribute("re",reservationAllByUser)
            return "userReservation"
        }
        else
        {
            model.addAttribute("userName",userDetails.username)
            service.removeReservation(searchReservationByNameTime)
            val reservationAll2 : List<reservation> = service.findReservationBy()
            val reservationAllByUser : List<reservation> = service.findEveryReservationById(reservationAll2,userDetails.username)
            model.addAttribute("re",reservationAllByUser)
            return "userReservation"
        }
    }
}