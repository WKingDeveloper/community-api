package com.wkd.communityapi.graphql.resolver.board

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import com.wkd.communityapi.model.board.BoardCreateParam
import com.wkd.communityapi.service.board.BoardService

@DgsComponent
class BoardMutation(
    private val boardService: BoardService
) {
    @DgsMutation
    fun createBoard(
        env: DgsDataFetchingEnvironment,
        @InputArgument param: BoardCreateParam
    ) = boardService.create(
        param
    )
}
