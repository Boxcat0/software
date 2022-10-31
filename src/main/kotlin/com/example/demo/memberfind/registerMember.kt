package com.example.demo.memberfind

import com.example.demo.account.AccountRole
import org.springframework.data.relational.core.mapping.Table
import javax.persistence.*



@Entity
@Table(name = "registerMember")
data class registerMember(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val number: Long? = -1,
        val name : String?,
        val passwords : String?,
        @Enumerated(EnumType.STRING)
        @ElementCollection(fetch = FetchType.EAGER)
        var roles: MutableSet<AccountRole>
)