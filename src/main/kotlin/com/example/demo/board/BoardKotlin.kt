package com.example.demo.board

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "board_kotlin2")
class BoardKotlin(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column(name = "title", nullable = false)
        var title: String,

        @Column(name = "contents", nullable = false, columnDefinition = "text")
        var contents: String,

        @Column(name = "reg_user_name", nullable = false, length = 10, updatable = false)
        var regUserName: String,

        @CreatedDate
        @Column(name = "reg_date", nullable = false, updatable = false)
        var regDate: LocalDateTime = LocalDateTime.now(),

        @LastModifiedDate
        @Column(name = "update_date", nullable = false)
        var updateDate: LocalDateTime = LocalDateTime.now()
)