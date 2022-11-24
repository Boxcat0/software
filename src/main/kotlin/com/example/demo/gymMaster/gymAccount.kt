package com.example.demo.gymMaster

import com.example.demo.account.AccountRole
import javax.persistence.*

@Entity
@Table(name = "gym_account")
data class gymAccount(
    @Id @GeneratedValue
    var number_gymaccount: Long? = null,//기본 키
    var id: String,
    var pw: String,
    var gym: String,
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    var roles: MutableSet<AccountRole>
)
