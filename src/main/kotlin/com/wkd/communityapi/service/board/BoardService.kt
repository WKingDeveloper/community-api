package com.wkd.communityapi.service.board

import com.wkd.communityapi.model.board.Board
import com.wkd.communityapi.model.board.BoardParam
import com.wkd.communityapi.repository.board.BoardRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
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
            .orElseThrow { RuntimeException("Board not found with id: $id") }
    }

    // todo: 응답 결과 부모->자식 구조로 변경
    fun getList(page: Int, size: Int): Page<Board> {
        return repository.findAll(PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "id")))
    }
}
