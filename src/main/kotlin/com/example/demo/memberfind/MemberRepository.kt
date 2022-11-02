package com.example.demo.memberfind

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository:CrudRepository<Member, Member> {
    @Query("select id,password from member")
    fun findmember(): List<Member>

}

