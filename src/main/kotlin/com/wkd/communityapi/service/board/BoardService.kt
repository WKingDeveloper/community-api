package com.wkd.communityapi.service.board

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
            displayOrder = param.displayOrder
        )

        return repository.save(board)
    }
}