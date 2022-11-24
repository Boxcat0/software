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
@RequestMapping("/admin")
class adminController(@Autowired val repository: reservationRepository,
                      @Autowired val service: reservationService) {

    @PostMapping("/delete_reservation2")
    fun deleteRev(@AuthenticationPrincipal userDetails: UserDetails,
                  reservation : reservation,
                  model: Model,
                  session : HttpSession
    ):String
    {

        val reservationAll : List<reservation> = repository.findBy()
        val reservationAllByNumber : reservation = service.findReservationByNumber(reservationAll,reservation.number_reservation)
        if(reservationAllByNumber.id =="null")
        {
            model.addAttribute("userName",userDetails.username)
            val reservationAll2 : List<reservation> = repository.findBy()
            model.addAttribute("re",reservationAll2)
            return "adminReservationCheck"
        }
        else{
            model.addAttribute("userName",userDetails.username)
            service.removeReservation(reservationAllByNumber)
            val reservationAll2 : List<reservation> = repository.findBy()
            model.addAttribute("re",reservationAll2)
            return "adminReservationCheck"
        }
    }
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
        return "adminReservationCheck"
    }
    @PostMapping("/Gym")
    fun findRevGym(@AuthenticationPrincipal userDetails: UserDetails,
                   reservation: reservation, model: Model, session: HttpSession
    ):String {
        val reservationAll : List<reservation> = repository.findBy()
        val reservationAllByGym : List<reservation> = service.findEveryReservationByGym(reservationAll,reservation.gym)
        model.addAttribute("userName",userDetails.username)
        model.addAttribute("re",reservationAllByGym)
        return "adminReservationCheck"
    }
    @PostMapping("/Name")
    fun findRevName(@AuthenticationPrincipal userDetails: UserDetails,
                    reservation: reservation, model: Model, session: HttpSession
    ):String
    {
        val reservationAll : List<reservation> = repository.findBy()
        println(reservation.name)
        val reservationAllByName : List<reservation> = service.findEveryReservationByName(reservationAll,reservation.name)
        model.addAttribute("userName",userDetails.username)
        model.addAttribute("re",reservationAllByName)
        return "adminReservationCheck"
    }
    @PostMapping("/reset")
    fun reset(@AuthenticationPrincipal userDetails: UserDetails,
              model: Model, session: HttpSession
    ):String{
        model.addAttribute("userName", userDetails.username)
        val userReservation : List<reservation> = service.findReservationBy()
        val userReservationFinal : List<reservation> = service.findEveryReservationById(userReservation, userDetails.username)
        model.addAttribute("re",userReservationFinal)
        return "adminReservationCheck"
    }

}