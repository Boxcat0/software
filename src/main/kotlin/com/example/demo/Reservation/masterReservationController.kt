package com.example.demo.Reservation

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
                                  @Autowired val repository: reservationRepository
) {

    @GetMapping("/reservationCheck")
    fun check(@AuthenticationPrincipal userDetails: UserDetails,
              session: HttpSession, model: Model
    ):String{
        model.addAttribute("userName",userDetails.username)
        model.addAttribute("GymId",session.getAttribute("GymId"))
        val gym : String = session.getAttribute("GymId").toString()
        val reservationAll : List<reservation> = repository.findreservationBy()
        val reservationAllByGym : List<reservation> = service.findEveryReservationByGym(reservationAll,gym)
        model.addAttribute("re",reservationAllByGym)
        return "reservationCheck"
    }

    @PostMapping("/search_reservation")
    fun findRev(@AuthenticationPrincipal userDetails: UserDetails,
                reservation : reservation,
                model: Model,
                session : HttpSession
    ):String{
        val reservationAll : List<reservation> = repository.findreservationBy()
        val reservationAllByTime : List<reservation> = service.findEveryReservationByTime(reservationAll,reservation.time)
        if(reservationAllByTime.isEmpty())
        {
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