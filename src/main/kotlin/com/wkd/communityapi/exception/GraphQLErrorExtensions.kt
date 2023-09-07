package com.wkd.communityapi.exception

interface GraphQLErrorExtensions {
    fun buildExtensions(): Map<String, Any>
}
