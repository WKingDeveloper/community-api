package com.wkd.communityapi.graphql.relay

fun interface CursorBuilder<T> {
    fun build(arg: T): String
}
