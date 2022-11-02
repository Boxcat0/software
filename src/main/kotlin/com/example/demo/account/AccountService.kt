package com.example.demo.account

import com.example.demo.reviewer.Review
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
    fun saveAccount(account: Account): Account//비밀번호 암호화 이후 저장
    {
        account.password = this.passwordEncoder.encode(account.password)
        return accountRepository.save(account)
    }
    fun findAccount(): List<Account> = accountRepository.findAccountBy()
    fun Accountpost(account : Account)
    {
        accountRepository.save(account)
    }

    override fun loadUserByUsername(id: String): UserDetails
    {
        return accountRepository.findByid(id)?.getAuthorities()
                ?: throw UsernameNotFoundException("$id Can Not Found")
    }
}