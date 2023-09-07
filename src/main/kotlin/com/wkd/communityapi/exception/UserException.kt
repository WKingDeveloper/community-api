package com.wkd.communityapi.exception

class NotFoundUserException(
    override val message: String = "유저 정보를 찾을 수 없습니다.",
) : BaseException(code = ErrorCode.NotFoundUser.code, message = message, originalError = null)
