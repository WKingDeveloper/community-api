package com.wkd.communityapi.exception

class NotFoundBoardException(
    override val message: String = "게시판 정보를 찾을 수 없습니다.",
    val boardId: Long? = null
) : BaseException(code = ErrorCode.NotFoundBoard.code, message = message, originalError = null)

