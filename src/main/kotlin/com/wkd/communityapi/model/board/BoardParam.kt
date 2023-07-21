package com.wkd.communityapi.model.board

data class BoardParam(
    val name: String,

    val parentBoardId: Long?,

    val indexNo: Int

)