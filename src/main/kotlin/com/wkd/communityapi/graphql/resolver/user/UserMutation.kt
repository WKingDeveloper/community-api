package com.wkd.communityapi.graphql.resolver.user

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import com.wkd.communityapi.annotation.Logger.Companion.logger
import com.wkd.communityapi.model.user.LoginParam
import com.wkd.communityapi.model.user.UserCreateParam
import com.wkd.communityapi.service.user.UserService

@DgsComponent
class UserMutation(
    private val userService: UserService
) {
    @DgsMutation
    fun createUser(
        env: DgsDataFetchingEnvironment,
        @InputArgument param: UserCreateParam
    ) {
        logger.info("UserMutation -> createUser() param : $param")
        userService.create(param)
    }

    @DgsMutation
    fun login(
        env: DgsDataFetchingEnvironment,
        @InputArgument param: LoginParam
    ) {
        logger.info("UserMutation -> login() param : $param")
        userService.login(param)
    }
}
