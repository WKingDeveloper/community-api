package com.wkd.communityapi.graphql.resolver.board

import com.netflix.graphql.dgs.*
import com.wkd.communityapi.annotation.Logger
import com.wkd.communityapi.annotation.Logger.Companion.logger
import com.wkd.communityapi.model.board.Board
import com.wkd.communityapi.service.board.BoardService

data class BoardContainer(
    val dummy: String = ""
)

@Logger
@DgsComponent
class BoardQuery(
    val boardService: BoardService
) {
    companion object {
        const val TYPE_NAME = "BoardContainer"
    }

    @DgsQuery
    fun boardContainer() = BoardContainer()

    @DgsData(parentType = TYPE_NAME)
    fun board(@InputArgument id: Long, env: DgsDataFetchingEnvironment): Board {
        logger.info("BoardQuery -> board() id : $id")
        return boardService.get(id)
    }

    @DgsData(parentType = TYPE_NAME)
    fun boards(
        env: DgsDataFetchingEnvironment,
    ): List<Board> {

        logger.info("BoardQuery -> boards()")

        return boardService.getList()
    }
}

