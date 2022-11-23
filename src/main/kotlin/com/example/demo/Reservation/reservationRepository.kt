package com.example.demo.Reservation

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.stereotype.Repository

@Repository
interface reservationRepository: JpaRepository<reservation, reservation> {
    @Query("select id,name, gym, times from reservation")
    fun findBy():List<reservation>
}