package com.example.demo.memberfind

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Table(name = "member")
data class Member(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var Id : String?,
    @Column("passwords")
    var passwords : String
)