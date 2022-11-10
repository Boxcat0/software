package com.example.demo.Event

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
@Table(name = "eventgym")//헬스장 받기 위한 임시 데이터
data class eventGym(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val number: Long? = null,
    @Column("gymId")
    val name : String,
    @Column("gymPosition")
    val position : String
)
