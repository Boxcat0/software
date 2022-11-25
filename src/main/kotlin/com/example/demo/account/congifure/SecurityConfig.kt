package com.example.demo.account.congifure

import com.example.demo.account.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class SecurityConfig(@Autowired private val accountService: AccountService,
                     @Autowired private val passwordEncoder: PasswordEncoder): WebSecurityConfigurerAdapter() {

    companion object {
        const val LOGIN_SUCCESS_URL: String = "/view/success"
        const val LOGOUT_SUCCESS_URL: String = "/home"
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
                .userDetailsService(accountService)
                .passwordEncoder(passwordEncoder)
        auth.eraseCredentials(false);//비밀번호  null 값 방지
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        http.logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl(LOGOUT_SUCCESS_URL)
            .deleteCookies("JSESSIONID","remember-me")
        http.anonymous()
                .and()
                .formLogin()
                .successForwardUrl(LOGIN_SUCCESS_URL)
                .and()
                .authorizeRequests()
                .antMatchers("/allaccount").access("hasRole('ADMIN')")
            .antMatchers("/adminPage").access("hasRole('ADMIN')")
            .antMatchers("/registerPage").access("hasRole('REGIST')")
            .antMatchers("/adminReviewDelete").access("hasRole('ADMIN')")
                .antMatchers("/board").access("hasRole('USER')")
            .antMatchers("/member_sign2").access("hasRole('REGIST')")
            .antMatchers("/Map2").access("hasRole('REGIST')")
            .antMatchers("/gymMasterRegist").access("hasRole('ADMIN')")
            .antMatchers("/reservationCheck").access("hasRole('GYM')")
                .anyRequest().authenticated()
    }
}