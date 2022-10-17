package com.example.demo.memberfind

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface MemberRepository:CrudRepository<Member, Member> {

    @Query("select id,passwords from member")
    fun findMember(): List<Member>

}

