package com.wkd.communityapi.graphql.relay

class Edge<T>(
    private val value: T,
    private val cursorBuilder: CursorBuilder<T>
) {
    val cursor: String
        get() = cursorBuilder.build(value)

    val node: T
        get() = value
}
