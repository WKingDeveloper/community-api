package com.wkd.communityapi.graphql.resolver.user

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import com.wkd.communityapi.annotation.Logger.Companion.logger
import com.wkd.communityapi.model.user.SignInParam
import com.wkd.communityapi.model.user.SignUpParam
import com.wkd.communityapi.model.user.User
import com.wkd.communityapi.service.user.UserService

@DgsComponent
class UserMutation(
    private val userService: UserService
) {
    @DgsMutation
    fun signUp(
        env: DgsDataFetchingEnvironment,
        @InputArgument param: SignUpParam
    ): User {
        logger.info("UserMutation -> signUp() param : $param")
        return userService.signUp(param)
    }

    @DgsMutation
    fun signIn(
        env: DgsDataFetchingEnvironment,
        @InputArgument param: SignInParam
    ): User {
        logger.info("UserMutation -> signIn() param : $param")
        return userService.signIn(param)
    }
}
