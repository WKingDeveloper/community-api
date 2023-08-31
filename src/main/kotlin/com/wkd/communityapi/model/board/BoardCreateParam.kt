package com.wkd.communityapi.model.board

data class BoardCreateParam(
    val name: String,

    val parentBoardId: Long?,

    val indexNo: Int
)
