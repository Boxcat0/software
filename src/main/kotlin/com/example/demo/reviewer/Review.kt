package com.example.demo.reviewer

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType


@Table("reviewtable")
data class Review(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val review:String?,
        val star: Double,
        val id : String?
)