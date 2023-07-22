package com.wkd.communityapi.exception

open class BaseException(
    val code: String,
    message: String,
    originalError: Throwable? = null
) : RuntimeException(message, originalError)
