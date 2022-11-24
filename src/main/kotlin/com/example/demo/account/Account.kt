package com.example.demo.account

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import java.util.stream.Collectors
import javax.persistence.*

@Entity
@Table(name = "Account")
data class Account(
        @Id @GeneratedValue
        var number_account: Long? = null,//기본 키
        var id: String,
        var password: String,
        @Enumerated(EnumType.STRING)
        @ElementCollection(fetch = FetchType.EAGER)
        var roles: MutableSet<AccountRole>
){
    fun getAuthorities(): User {
        return User(
                this.id,this.password,
                this.roles.stream().map { role -> SimpleGrantedAuthority("ROLE_$role") }.collect(Collectors.toSet())
        )
    }
}