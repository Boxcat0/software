package com.example.demo.Reservation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.servlet.http.HttpSession

@Controller
class reservationController(@Autowired val service: reservationService,
                            @Autowired val repository: reservationRepository
) {
    @GetMapping("/reservation")
    fun intoRev(@AuthenticationPrincipal userDetails: UserDetails,
                model: Model,
                session: HttpSession
                ):String{
        val now : LocalDate = LocalDate.now()
        val formatter : DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val todayDay : String = now.format(formatter)
        model.addAttribute("today",todayDay)
        model.addAttribute("userName",userDetails.username)
        model.addAttribute("GymId",session.getAttribute("GymId"))
        return "rev"
    }

    @PostMapping("/reservation")
    fun intoRev2(session: HttpSession,reservation: reservation,
                 model: Model,
                 @AuthenticationPrincipal userDetails: UserDetails
    ):String{
        val now : LocalDate = LocalDate.now()
        val formatter : DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val todayDay : String = now.format(formatter)
        if(reservation.times < todayDay)
        {
            model.addAttribute("today",todayDay)
            model.addAttribute("userName",userDetails.username)
            model.addAttribute("GymId",session.getAttribute("GymId"))
            return "rev"
        }
        else
        {
            val reservationAll : List<reservation> = repository.findBy()
            val reservationId : reservation = service.findEveryReservationByNameTime(reservationAll,reservation.name,reservation.times,reservation.gym)
            if(reservationId.id == "null" && reservation.times <= reservation.endTimes)
            {
                service.makeReservation(reservation)
                model.addAttribute("userName",userDetails.username)
                model.addAttribute("GymId",session.getAttribute("GymId"))
                model.addAttribute("GymId",session.getAttribute("GymId"))
                return "eventPage"
            }
            else{
                model.addAttribute("today",todayDay)
                model.addAttribute("userName",userDetails.username)
                model.addAttribute("GymId",session.getAttribute("GymId"))
                return "rev"
            }
        }
    }
}