package com.example.demo.account

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import java.util.stream.Collectors

class CustomUserDetails private constructor(
        private val userName: String,
        private val password: String,
        private val roles: MutableSet<AccountRole>,
        val createDt: LocalDateTime,
        val visitCount: Int = 5) : UserDetails {

    companion object {
        fun from(account: Account): CustomUserDetails {
            return with(account) {
                CustomUserDetails(userName = id,
                        password = password,
                        roles = roles,
                        createDt = createDt)
            }
        }
    }

    override fun getUsername(): String {
        return userName
    }

    override fun getPassword(): String {
        return password
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return roles.stream().map { role -> SimpleGrantedAuthority("ROLE_$role") }.collect(Collectors.toSet())
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}