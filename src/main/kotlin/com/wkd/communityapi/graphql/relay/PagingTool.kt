package com.wkd.communityapi.graphql.relay

import java.util.*

class PagingTool {
    companion object {
        fun <T> convertToCursor(name: String, id: T): String {
            return Base64.getEncoder().encodeToString("${name}:${id.toString()}".toByteArray())
        }
    }
}
