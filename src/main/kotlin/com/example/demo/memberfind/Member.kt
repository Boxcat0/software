package com.example.demo.memberfind

import com.example.demo.account.Account
import com.example.demo.account.AccountRole
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import javax.persistence.*

@Table(name = "member")
data class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column("Id")
    var Id : String?,
    @Column("password")
    var password : String?,
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    var roles: MutableSet<AccountRole>
)