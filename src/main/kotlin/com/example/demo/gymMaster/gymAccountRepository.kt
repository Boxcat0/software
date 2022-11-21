package com.example.demo.gymMaster

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface gymAccountRepository: JpaRepository<gymAccount, gymAccount> {

    @Query("select id from gym_account") // 쿼리문을 기준으로 리스트 작성
    fun findGymAccountBy(): List<gymAccount>
}