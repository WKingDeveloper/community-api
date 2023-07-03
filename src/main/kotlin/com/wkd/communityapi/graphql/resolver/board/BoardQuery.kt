package com.wkd.communityapi.graphql.resolver.board

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.wkd.akiriapi.annotation.Logger
import com.wkd.akiriapi.annotation.Logger.Companion.logger
import com.wkd.communityapi.graphql.relay.Connection
import com.wkd.communityapi.graphql.relay.PagingTool
import com.wkd.communityapi.model.board.Board
import com.wkd.communityapi.service.board.BoardService

@Logger
@DgsComponent
class BoardQuery(
    val boardService: BoardService
) {

    @DgsQuery
    fun board(@InputArgument id: Long, env: DgsDataFetchingEnvironment): Board {
        logger.info("BoardQuery -> board()")
        return boardService.get(id)
    }

    @DgsQuery
    fun boards(
        env: DgsDataFetchingEnvironment,
        @InputArgument page: Int? = 1,
        @InputArgument size: Int? = 10,
    ): Connection<Board> {

        logger.info("NotificationResolver -> boards() : page : $page, size: $size")

        val boards = boardService.getList(page ?: 1, size ?: 10)

        val boardConnection = Connection(
            boards
        ) {
            PagingTool.convertToCursor(Board::class.java.simpleName, it.id)
        }

        return boardConnection
    }
}
