package com.wkd.communityapi.service.board

import com.wkd.communityapi.exception.NotFoundBoardException
import com.wkd.communityapi.model.board.Board
import com.wkd.communityapi.model.board.BoardParam
import com.wkd.communityapi.repository.board.BoardRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class BoardService(
    private val repository: BoardRepository
) {

    fun create(param: BoardParam): Board {
        val board = Board(
            name = param.name,
            parentBoardId = param.parentBoardId,
            indexNo = param.indexNo
        )

        return repository.save(board)
    }

    fun get(id: Long): Board {
        return repository.findById(id)
            .orElseThrow { NotFoundBoardException() }
    }

    fun getList(): List<Board> {
        return generateResponse(repository.findAll())
    }

    private fun generateResponse(boards: List<Board>): List<Board> {
        var parentTags: MutableList<Board> = mutableListOf()

        boards.forEach { board ->
            if (board.parentBoardId == null) {
                parentTags.add(board)
            } else {
                val parentTag = parentTags.filter { tag -> tag.id == board.parentBoardId }.first()
                parentTag.childTags.add(board)
            }
        }
        return parentTags;
    }
}
