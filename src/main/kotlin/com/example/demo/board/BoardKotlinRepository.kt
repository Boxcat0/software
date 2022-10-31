package com.example.demo.board

import com.example.demo.board.BoardKotlin
import org.springframework.data.jpa.repository.JpaRepository

interface BoardKotlinRepository: JpaRepository<BoardKotlin, Int>
