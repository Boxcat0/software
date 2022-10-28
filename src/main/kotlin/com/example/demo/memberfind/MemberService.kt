package com.example.demo.memberfind

import org.springframework.stereotype.Service


@Service
class MemberService(val db: MemberRepository) {
    fun findMember(): List<Member> = db.findmember()
    fun post(member: Member)
    {
        db.save(member)
    }
}
