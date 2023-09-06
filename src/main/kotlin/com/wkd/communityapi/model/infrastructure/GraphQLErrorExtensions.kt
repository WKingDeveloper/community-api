package com.wkd.communityapi.model.infrastructure

interface GraphQLErrorExtensions {
    fun buildExtensions(): Map<String, Any>
}
