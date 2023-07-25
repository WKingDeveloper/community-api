package com.wkd.communityapi.repository.board

import com.wkd.communityapi.model.board.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository : JpaRepository<Board, Long> {

    @Query("SELECT b FROM Board b WHERE b.status = 1 ORDER BY CASE WHEN b.parentBoardId IS NULL THEN 0 ELSE 1 END, b.parentBoardId, b.indexNo")
    override fun findAll(): MutableList<Board>
}