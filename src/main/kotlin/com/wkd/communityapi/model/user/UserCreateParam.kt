package com.wkd.communityapi.model.user

import com.wkd.communityapi.model.auth.AuthorityLevel

data class UserCreateParam(
    val email: String,

    val password: String,

    val authorityLevel: AuthorityLevel?
)
