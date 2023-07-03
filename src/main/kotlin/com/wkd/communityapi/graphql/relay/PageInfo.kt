package com.wkd.communityapi.graphql.relay

import org.springframework.data.domain.Page

class PageInfo<T>(
    private val page: Page<T>,
    private val cursorBuilder: CursorBuilder<T>
) {
    val hasNextPage: Boolean
        get() = page.hasNext()

    val hasPreviousPage: Boolean
        get() = page.hasPrevious()

    val startCursor: String
        get() = page.content.firstOrNull()?.let {
            cursorBuilder.build(it)
        } ?: ""

    val endCursor: String
        get() = page.content.lastOrNull()?.let {
            cursorBuilder.build(it)
        } ?: ""
}

