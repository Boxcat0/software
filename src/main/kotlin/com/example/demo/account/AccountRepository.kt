package com.example.demo.account

import com.example.demo.memberfind.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jdbc.repository.query.Query

interface AccountRepository: JpaRepository<Account, Account>//무조건 <받는 엔티티, 엔티티형식> 유지
{
    fun findByid(id: String): Account?//아이디 기준으로 찾기
    @Query("select id from account") // 쿼리문을 기준으로 리스트 작성
    fun findAccountBy(): List<Account>
}