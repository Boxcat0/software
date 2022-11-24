package com.example.demo.reviewer


import org.springframework.data.relational.core.mapping.Column
import javax.persistence.*

@Entity
@Table(name = "Review")
data class Review(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)//Default value 문제 생기면 AUTO로 바꿔주면 된다.
        var number_review : Long? = null,
        @Column("id")
        var id : String,
        @Column("star")
        var star: Double?,
        @Column("reviews")
        var reviews:String,
        @Column("gym_name")
        var gym : String?
)