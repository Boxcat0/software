package com.example.demo.gymMaster

import com.example.demo.account.AccountRole
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class gymAccountService(@Autowired private val repository: gymAccountRepository) {
    @Transactional
    fun saveAccount(gymAccount: gymAccount):gymAccount
    {
        return repository.save(gymAccount)
    }
    fun gymRemove(account : gymAccount){
        repository.delete(account)
    }
    fun findGymAccount(): List<gymAccount> = repository.findGymAccountBy()
    fun searchAccountByNumber(number:Long?, account: List<gymAccount>):gymAccount
    {
        for(i in 0..account.size-1)
        {
            if(account[i].number == number)
            {
                return account[i]
            }
        }
        return gymAccount(null,"null","null","null",mutableSetOf(AccountRole.GYM))
    }
    fun searchAccount(gym: String, account : List<gymAccount>): gymAccount
    {
        for(i in 0..account.size-1)
        {
            if(account[i].gym == gym)
            {
                return account[i]
            }
        }
        return gymAccount(null,"null","null","null",mutableSetOf(AccountRole.GYM))
    }
    fun searchAccountById(id: String, account : List<gymAccount>): gymAccount
    {
        for(i in 0..account.size-1)
        {
            if(account[i].id == id)
            {
                return account[i]
            }
        }
        return gymAccount(null,"null","null","null",mutableSetOf(AccountRole.GYM))
    }
}