package com.example.demo.memberfind

import org.springframework.data.repository.CrudRepository

interface registerMemberRepository : CrudRepository<registerMember, Long> {
}