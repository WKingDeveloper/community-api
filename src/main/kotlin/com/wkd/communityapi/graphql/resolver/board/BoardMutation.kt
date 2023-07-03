package com.wkd.communityapi.graphql.resolver.board

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import com.wkd.communityapi.model.board.BoardParam
import com.wkd.communityapi.service.board.BoardService

@DgsComponent
class BoardMutation(
    private val service: BoardService,
) {
    @DgsMutation
    fun createBoard(
        env: DgsDataFetchingEnvironment,
        @InputArgument param: BoardParam
    ) = service.create(
        param
    )
}
