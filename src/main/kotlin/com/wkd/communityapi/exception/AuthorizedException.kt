package com.wkd.communityapi.exception

import com.wkd.communityapi.model.infrastructure.AuthorityLevel
import com.wkd.communityapi.model.infrastructure.GraphQLErrorExtensions

class NotAuthorizedException(
    private val requires: Array<AuthorityLevel>,
    private val present: AuthorityLevel,
) : RuntimeException("요청하신 작업에 대한 권한이 없습니다."), GraphQLErrorExtensions {
    override fun buildExtensions(): Map<String, Any> {
        val data = HashMap<String, Any>()
        data["requires"] = requires
        data["present"] = present
        data["code"] = "NOT_AUTHORIZED"
        return data
    }
}
