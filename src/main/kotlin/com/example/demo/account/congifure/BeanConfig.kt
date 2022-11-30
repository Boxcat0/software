package com.example.demo.account.congifure

import com.example.demo.account.Account
import com.example.demo.account.AccountRole
import com.example.demo.account.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
class BeanConfig {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }
    /*@Bean // 데이터 날아가는거 대비 비상 아이디 선언
    fun applicationRunner(): ApplicationRunner {
        return object : ApplicationRunner {
            @Autowired
            lateinit var accountService: AccountService
            @Throws(Exception::class)
            override fun run(args: ApplicationArguments) {
                val regist = Account(null,
                    "register123",
                    "register1234",
                    mutableSetOf(
                        AccountRole.REGIST
                    )
                )
                accountService.saveAccount(regist)
            }
        }
    }
    @Bean // 데이터 날아가는거 대비 비상 아이디 선언
    fun applicationRunner2(): ApplicationRunner {
        return object : ApplicationRunner {
            @Autowired
            lateinit var accountService: AccountService
            @Throws(Exception::class)
            override fun run(args: ApplicationArguments) {
                val admin = Account(null,
                        "admin12",
                        "admin1234",
                        mutableSetOf(
                                AccountRole.ADMIN,
                                AccountRole.USER,
                                AccountRole.REGIST,
                            AccountRole.GYM
                        )
                )
                accountService.saveAccount(admin)
            }
        }
    }*/
}