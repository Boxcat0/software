package com.example.demo.Reservation

import org.springframework.data.relational.core.mapping.Table
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
@Table(name="reservation")
data class reservation(
    @Id @GeneratedValue
    var number: Long? = null,
    var id : String,
    var name: String,
    var gym : String,
    var time : String
)
