package com.example.demo.account

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(@Autowired private val accountRepository: AccountRepository,
                     @Autowired private val passwordEncoder: PasswordEncoder): UserDetailsService {
    @Transactional
    fun saveAccount(account: Account): Account
    {
        account.password = this.passwordEncoder.encode(account.password)
        return accountRepository.save(account)
    }

    override fun loadUserByUsername(id: String): UserDetails
    {
        return accountRepository.findByid(id)?.getAuthorities()
                ?: throw UsernameNotFoundException("$id Can Not Found")
    }
}