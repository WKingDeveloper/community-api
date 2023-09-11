package com.wkd.communityapi.graphql.resolver.user

import com.netflix.graphql.dgs.*
import com.wkd.communityapi.annotation.Logger
import com.wkd.communityapi.annotation.Logger.Companion.logger
import com.wkd.communityapi.graphql.relay.Connection
import com.wkd.communityapi.graphql.relay.PagingTool
import com.wkd.communityapi.model.post.Post
import com.wkd.communityapi.model.user.User
import com.wkd.communityapi.service.user.UserService

data class UserContainer(
    val dummy: String = ""
)

@Logger
@DgsComponent
class UserQuery(
    val userService: UserService
) {
    companion object {
        const val TYPE_NAME = "UserContainer"
    }

    @DgsQuery
    fun userContainer() = UserContainer()

    @DgsData(parentType = TYPE_NAME)
    fun user(@InputArgument id: Long, env: DgsDataFetchingEnvironment): User {
        logger.info("UserQuery -> user() id : $id")
        return userService.getById(id)
    }

    @DgsData(parentType = TYPE_NAME)
    fun users(
        env: DgsDataFetchingEnvironment,
        @InputArgument page: Int? = 1,
        @InputArgument size: Int? = 10,
    ): Connection<User> {

        logger.info("UserQuery -> users() : page : $page, size: $size")

        val users = userService.getList(page ?: 1, size ?: 10)

        val userConnection = Connection(
            users
        ) {
            PagingTool.convertToCursor(Post::class.java.simpleName, it.id)
        }

        return userConnection
    }
}
