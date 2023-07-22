package com.wkd.communityapi.exception

class NotFoundPostException(
    override val message: String = "게시글 정보를 찾을 수 없습니다.",
    val postId: Long? = null
) : BaseException(code = ErrorCode.NotFoundBoard.code, message = message, originalError = null)

