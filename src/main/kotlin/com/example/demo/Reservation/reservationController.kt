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
class reservationController(@Autowired val service: reservationService,
                            @Autowired val repository: reservationRepository
) {
    @GetMapping("/resevation")
    fun intoRev(@AuthenticationPrincipal userDetails: UserDetails,
                model: Model,
                session: HttpSession
                ):String{
        model.addAttribute("userName",userDetails.username)
        model.addAttribute("GymId",session.getAttribute("GymId"))
        return "rev"
    }

    @PostMapping("/resevation")
    fun intoRev2(session: HttpSession,reservation: reservation,
                 model: Model,
                 @AuthenticationPrincipal userDetails: UserDetails
    ):String{
        println(reservation.time)
        val reservationAll : List<reservation> = repository.findreservationBy()
        val reservationId : List<reservation> = service.findEveryReservationById(reservationAll,reservation.id,reservation.time)
        println(reservationId)
        if(reservationId.isEmpty())
        {
            service.makeReservation(reservation)
            session.removeAttribute("GymId")
            session.removeAttribute("GymPosition")
            return "map_click"
        }
        else{
            model.addAttribute("userName",userDetails.username)
            model.addAttribute("GymId",session.getAttribute("GymId"))
            return "rev"
        }
    }
}