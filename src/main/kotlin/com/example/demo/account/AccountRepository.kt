package com.example.demo.account

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jdbc.repository.query.Query

interface AccountRepository: JpaRepository<Account, Long> {
    fun findByid(id: String): Account?

   /* @Query("select id, passwords from member where :id")
    fun findId(id: String): String*/
}