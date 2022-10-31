package com.example.demo.reviewer


import org.springframework.data.relational.core.mapping.Table
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import com.example.demo.memberfind.Member


@Table("reviewtable")
data class Review(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val review:String?,
        val star: Double,
        val gym_id : String?,

        @ManyToOne(targetEntity = Member::class)
        var id : Member? = null
)