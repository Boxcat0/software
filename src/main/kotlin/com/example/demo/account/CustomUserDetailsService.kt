package com.example.demo.account

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val accountRepository: AccountRepository,
                               private val passwordEncoder: PasswordEncoder) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return accountRepository.findByid(username)?.let { CustomUserDetails.from(it) }
                ?: throw UsernameNotFoundException("$username Can Not Found")
    }
}