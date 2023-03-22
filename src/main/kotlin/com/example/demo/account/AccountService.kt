package com.example.demo.account

import com.example.demo.reviewer.Review
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.collections.ArrayList

@Service
class AccountService(@Autowired private val accountRepository: AccountRepository,
                     @Autowired private val passwordEncoder: PasswordEncoder,
                     @Autowired private val accountRepository2: AccountRepository2
): UserDetailsService {
    @Transactional
    fun saveAccount(account: Account): Account//비밀번호 암호화 이후 저장
    {
        account.password = this.passwordEncoder.encode(account.password)
        return accountRepository.save(account)
    }
    fun accountToaccountSave(account: Account):AccountSave2
    {
        return AccountSave2(account.number_account?.minus(1),account.id,account.password)
    }
    fun saveAccount2(account: AccountSave2):AccountSave2
    {
        return accountRepository2.save(account)
    }
    fun searchAccount(id: String, account : List<Account>): Account
    {
        for(i in 0..account.size-1)
        {
            if(account[i].id == id)
            {
                return account[i]
                break
            }
        }
        return Account(null,"null","null",mutableSetOf(AccountRole.USER))
    }
    fun findAccount(): List<Account> = accountRepository.findAccountBy()
    fun findAccountSave():List<AccountSave2> = accountRepository2.findAccountSaveBy()

    fun findRoleAccount(id:String):Account{
        val list:List<Account> = findAccount()
        for(i in 0..list.size-1)
        {
            if(list[i].id == id)
            {
                return list[i]
            }
        }
        return  Account(null,"null","null",mutableSetOf(AccountRole.USER))
    }
    fun accountpost(account : Account)
    {
        accountRepository.save(account)
    }

    fun remove(account : Account){
        accountRepository.delete(account)
    }
    fun findAccountSaveById(account: List<AccountSave2>,id:String):AccountSave2 {
        for (i in 0..account.size - 1) {
            if (account[i].id == id) {
                return account[i]
            }
        }
        return AccountSave2(null, "null", "")
    }
    fun remove2(account: AccountSave2){
        accountRepository2.delete(account)
    }

    override fun loadUserByUsername(id: String): UserDetails
    {
        return accountRepository.findById(id)?.getAuthorities()
                ?: throw UsernameNotFoundException("$id Can Not Found")
    }
}