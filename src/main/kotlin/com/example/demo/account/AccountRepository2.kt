package com.example.demo.account

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository2 : JpaRepository<AccountSave2, AccountSave2> {
    @Query("select * from account_save") // 쿼리문을 기준으로 리스트 작성
    fun findAccountSaveBy(): List<AccountSave2>
}