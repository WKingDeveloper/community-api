package com.wkd.communityapi.graphql.resolver.post

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import com.wkd.communityapi.model.post.PostCreateParam
import com.wkd.communityapi.service.post.PostService

@DgsComponent
class PostMutation(
    private val postService: PostService
) {
    @DgsMutation
    fun createPost(
        env: DgsDataFetchingEnvironment,
        @InputArgument param: PostCreateParam
    ) = postService.create(
        param
    )
}
