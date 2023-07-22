package com.wkd.communityapi.graphql.resolver.post

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.wkd.communityapi.annotation.Logger
import com.wkd.communityapi.annotation.Logger.Companion.logger
import com.wkd.communityapi.graphql.relay.Connection
import com.wkd.communityapi.graphql.relay.PagingTool
import com.wkd.communityapi.model.post.Post
import com.wkd.communityapi.service.post.PostService

@Logger
@DgsComponent
class PostQuery(
    val postService: PostService
) {

    @DgsQuery
    fun post(@InputArgument id: Long, env: DgsDataFetchingEnvironment): Post {
        logger.info("postQuery -> post()")
        return postService.get(id)
    }

    @DgsQuery
    fun posts(
        env: DgsDataFetchingEnvironment,
        @InputArgument page: Int? = 1,
        @InputArgument size: Int? = 10,
    ): Connection<Post> {

        logger.info("PostQuery -> posts() : page : $page, size: $size")

        val posts = postService.getList(page ?: 1, size ?: 10)

        val postConnection = Connection(
            posts
        ) {
            PagingTool.convertToCursor(Post::class.java.simpleName, it.id)
        }

        return postConnection
    }
}
