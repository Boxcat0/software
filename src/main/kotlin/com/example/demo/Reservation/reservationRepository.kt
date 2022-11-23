package com.example.demo.Reservation

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface reservationRepository: JpaRepository<reservation, reservation> {
    @Query("select gym, id, name, time from reservation")
    fun findreservationBy() : List<reservation>
}