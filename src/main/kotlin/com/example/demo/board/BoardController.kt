package com.example.demo.board

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class BoardController (
        @Autowired
        private val boardService: BoardService
        )
{
    @PostMapping("/board")
    fun save(@RequestBody paramTO:BoardParamTo) = boardService.saveOfBoard(paramTO)

    @GetMapping("/board")
    fun list() = boardService.getAllBoardList()
    fun board():String{
        return "board"
    }

    @GetMapping("/board/{id}")
    fun detail(@PathVariable id: Int) = boardService.getBoardById(id)

    @PutMapping("/board/{id}")
    fun update(@PathVariable id: Int, @RequestBody paramTO: BoardParamTo) = boardService.updateOfBoardById(id, paramTO)

    @DeleteMapping("/board/{id}")
    fun delete(@PathVariable id: Int) = boardService.deleteOfBoardById(id)
}