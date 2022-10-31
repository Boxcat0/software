package com.example.demo.memberfind

import com.example.demo.account.AccountRole
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import javax.persistence.*

@Table(name = "member")
data class Member(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var Id : String?,
    @Column("passwords")
    var passwords : String,
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    var roles: MutableSet<AccountRole>
)