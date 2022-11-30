package com.example.demo.account

import org.springframework.data.relational.core.mapping.Table
import javax.persistence.*

@Entity
@Table(name="AccountSave")
data class AccountSave2(
    @Id @GeneratedValue
    var number_account2: Long? = null,//기본 키
    var id: String,
    var password: String,
)
