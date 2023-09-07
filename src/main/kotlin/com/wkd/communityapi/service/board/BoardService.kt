package com.wkd.communityapi.service.board

import com.wkd.communityapi.exception.NotFoundBoardException
import com.wkd.communityapi.model.board.Board
import com.wkd.communityapi.model.board.BoardCreateParam
import com.wkd.communityapi.repository.board.BoardRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class BoardService(
    private val repository: BoardRepository
) {

    @Transactional
    fun create(param: BoardCreateParam): Board {
        val board = Board(
            name = param.name,
            parentBoardId = param.parentBoardId,
            indexNo = param.indexNo
        )

        return repository.save(board)
    }

    fun get(id: Long): Board {
        val boards = repository.findByIdWithChildBoards(id)
        if (boards.isEmpty()) throw NotFoundBoardException()
        var board = boards[0]

        if (boards.size > 1) {
            boards.forEach {
                if (it.parentBoardId != null) board.childBoards.add(it)
            }
        }
        return board
    }

    fun getList(): List<Board> {
        return generateResponse(repository.findAll())
    }

    private fun generateResponse(boards: List<Board>): List<Board> {
        var parentBoards: MutableList<Board> = mutableListOf()

        boards.forEach { board ->
            if (board.parentBoardId == null) {
                parentBoards.add(board)
            } else {
                val parentBoard = parentBoards.filter { tag -> tag.id == board.parentBoardId }.first()
                parentBoard.childBoards.add(board)
            }
        }
        return parentBoards;
    }
}
