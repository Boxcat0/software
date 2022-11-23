package com.example.demo.Reservation

import org.springframework.data.jpa.repository.Temporal
import org.springframework.data.relational.core.mapping.Table
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.TemporalType


@Entity
@Table(name="reservation")
data class reservation(
    @Id @GeneratedValue
    var number: Long? = null,
    var id : String,
    var name: String,
    var gym : String,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var times : String
)
