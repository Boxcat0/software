package com.example.demo.memberfind

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import javax.persistence.EmbeddedId
import javax.persistence.GeneratedValue

@Table(name = "member")
data class Member(
    @EmbeddedId @GeneratedValue
    @Column("Id")
    var Id : String?,
    @Column("passwords")
    var passwords : String
)

