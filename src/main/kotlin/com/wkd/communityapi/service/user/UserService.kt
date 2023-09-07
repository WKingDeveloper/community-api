package com.wkd.communityapi.service.user

import com.wkd.communityapi.annotation.Logger
import com.wkd.communityapi.exception.NotFoundUserException
import com.wkd.communityapi.model.auth.AuthorityLevel
import com.wkd.communityapi.model.user.User
import com.wkd.communityapi.model.user.UserCreateParam
import com.wkd.communityapi.repository.user.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Logger
@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional
    fun create(param: UserCreateParam): User {
        val user = User(
            email = param.email,
            password = param.password,
            authorityLevel = param.authorityLevel ?: AuthorityLevel.USER
        )

        return userRepository.save(user)
    }

    fun getById(id: Long): User {
        return userRepository.findById(id)
            .orElseThrow { NotFoundUserException() }
    }
}
