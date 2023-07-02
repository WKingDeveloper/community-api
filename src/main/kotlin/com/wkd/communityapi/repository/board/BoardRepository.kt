package com.wkd.communityapi.repository.board

import com.wkd.communityapi.model.board.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository : JpaRepository<Board, Long> {
}