package com.wkd.communityapi.graphql.resolver.user

import com.netflix.graphql.dgs.*
import com.wkd.communityapi.annotation.Logger
import com.wkd.communityapi.annotation.Logger.Companion.logger
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
        logger.info("UserQuery -> post()")
        return userService.getById(id)
    }
}
