package com.example.demo.board

import com.example.demo.board.BoardDetailVo
import com.example.demo.board.BoardParamTo
import com.example.demo.board.BoardVO


interface BoardService {
    fun saveOfBoard(paramTO: BoardParamTo)
    fun getAllBoardList(): List<BoardVO>
    fun getBoardById(id: Int): BoardDetailVo
    fun updateOfBoardById(id: Int, paramTO: BoardParamTo)
    fun deleteOfBoardById(id: Int)
}