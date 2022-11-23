package com.example.demo.Reservation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

@Service
class reservationService(@Autowired val repository: reservationRepository) {
    @Transactional
    fun makeReservation(reservation: reservation):reservation
    {
        return repository.save(reservation)
    }
    fun findReservationBy():List<reservation>
    {
        return repository.findBy()
    }

    fun removeReservation(reservation: reservation)
    {
        repository.delete(reservation)
    }
    fun findEveryReservationByTime(reservation : List<reservation>,times:String):List<reservation>
    {
        println(reservation)
        val reservationId = ArrayList<reservation>()
        println(reservationId.size)
        if(reservation.size == 0)
        {
            return reservationId
        }
        else
        {
            for(i in 0..reservation.size-1)
            {
                if(reservation[i].times == times)
                {
                    reservationId.add(reservation[i])
                }
            }
            return reservationId
        }
    }
    fun findEveryReservationByGym(reservation : List<reservation>,gym:String):List<reservation>
    {
        val reservationId = ArrayList<reservation>()
        if(reservation.size == 0)
        {
            return reservationId
        }
        else
        {
            for(i in 0..reservation.size-1)
            {
                if(reservation[i].gym == gym)
                {
                    reservationId.add(reservation[i])
                }
            }
            return reservationId
        }
    }
    fun findEveryReservationById(reservation : List<reservation>,name:String,times: String):List<reservation>
    {
        println(reservation)
        val reservationId = ArrayList<reservation>()
        println(reservationId.size)
        if(reservation.size == 0)
        {
            return reservationId
        }
        else
        {
            for(i in 0..reservation.size-1)
            {
                if(reservation[i].name == name && reservation[i].times == times)
                {
                    reservationId.add(reservation[i])
                }
            }
            return reservationId
        }
    }
}