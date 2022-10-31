package com.example.demo.board

import org.springframework.stereotype.Service
import com.example.demo.board.BoardDetailVo
import com.example.demo.board.BoardParamTo
import com.example.demo.board.BoardVO
import java.time.LocalDateTime


@Service
class BoardServiceimpl(
        private val boardKotlinRepository: BoardKotlinRepository
): BoardService {
    override fun saveOfBoard(paramTo: BoardParamTo){
        val board = with(paramTo) {
            BoardKotlin(
                    title = title,
                    contents = contents,
                    regUserName = regUserName
            )
        }
        boardKotlinRepository.save(board)
    }
    override fun getBoardById(id: Int): BoardDetailVo {
        val board = boardKotlinRepository
                .findById(id)
                .orElseThrow { NoExistBoardException("no exist board! id is $id") }

        return with(board) {
            BoardDetailVo(
                    id = id,
                    title = title,
                    contents = contents,
                    regUserName = regUserName,
                    regDate = regDate,
                    updateDate = updateDate
            )
        }
    }
    override fun updateOfBoardById(id: Int, paramTO: BoardParamTo) {
        val board = boardKotlinRepository
                .findById(id)
                .orElseThrow { NoExistBoardException("no exist board! id is $id") }

        boardKotlinRepository.save(
                board.apply {
                    title = paramTO.title
                    contents = paramTO.contents
                    updateDate = LocalDateTime.now()
                }
        )
    }

    override fun deleteOfBoardById(id: Int) {
        boardKotlinRepository
                .findById(id)
                .ifPresent { boardKotlinRepository.delete(it) }
    }

    override fun getAllBoardList(): List<BoardVO> {
        return boardKotlinRepository.findAll().map {
            BoardVO(
                    id = it.id!!,
                    title = it.title,
                    regUserName =  it.regUserName,
                    regDate = it.regDate,
                    updateDate = it.updateDate
            )
        }
    }
}