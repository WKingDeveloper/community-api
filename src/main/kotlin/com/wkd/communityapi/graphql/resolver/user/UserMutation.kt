package com.wkd.communityapi.graphql.resolver.user

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
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
    ) = userService.create(
        param
    )
}
