package com.example.demo.memberfind

import com.example.demo.account.Account
import org.springframework.stereotype.Service


@Service
class MemberService(val db: MemberRepository) {
    fun findMember(): List<Member> = db.findmember()
    fun post(member: Member)
    {
        db.save(member)
    }
}
