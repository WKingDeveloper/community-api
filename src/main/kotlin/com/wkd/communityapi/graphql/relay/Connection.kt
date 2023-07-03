package com.wkd.communityapi.graphql.relay

import org.springframework.data.domain.Page

class Connection<T>(
    private val page: Page<T>,
    private val cursorBuilder: CursorBuilder<T>
) {
    val totalCount: Long
        get() = page.totalElements

    val pageInfo: PageInfo<T>
        get() = PageInfo(page = page, cursorBuilder = cursorBuilder)

    val edges: List<Edge<T>>
        get() = page.content.map { Edge(value = it, cursorBuilder = cursorBuilder) }
}
