package com.wkd.communityapi.exception

enum class ErrorCode(val code: String) {
    NotFoundBoard("Board404001"),
    NotFoundPost("Post404001"),
    BadRequestPost("Post400001"),
    NotFoundUser("User404001"),
}
