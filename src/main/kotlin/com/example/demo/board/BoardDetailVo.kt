package com.example.demo.board

import java.time.LocalDateTime

data class BoardDetailVo(
        val id: Int,
        val title: String,
        val contents: String,
        val regUserName: String,
        val regDate: LocalDateTime,
        val updateDate: LocalDateTime
)
