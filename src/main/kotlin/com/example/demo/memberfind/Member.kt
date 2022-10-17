package com.example.demo.memberfind

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table


@Table(name = "member")
data class Member(@Id var id:String?, var passwords:String?)
/*data class Member(
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column("Id")
    var Id : String?,
    @Column("passwords")
    var passwords : String
)*/


