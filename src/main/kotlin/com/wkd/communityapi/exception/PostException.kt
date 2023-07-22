package com.wkd.communityapi.exception

class NotFoundPostException(
    override val message: String = "게시글 정보를 찾을 수 없습니다.",
) : BaseException(code = ErrorCode.NotFoundBoard.code, message = message, originalError = null)

class BadRequestPostCreateException(
    override val message: String = "게시글을 생성할 수 없습니다. 요청 정보를 확인해주세요.",
) : BaseException(code = ErrorCode.BadRequestPost.code, message = message, originalError = null)
