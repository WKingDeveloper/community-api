package com.wkd.communityapi.service.user

import com.wkd.communityapi.annotation.Logger
import com.wkd.communityapi.exception.NotFoundUserException
import com.wkd.communityapi.model.auth.AuthorityLevel
import com.wkd.communityapi.model.user.SignInParam
import com.wkd.communityapi.model.user.SignUpParam
import com.wkd.communityapi.model.user.User
import com.wkd.communityapi.repository.user.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Logger
@Service
class UserService(
    private val userRepository: UserRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    @Transactional
    fun signUp(param: SignUpParam): User {
        val user = User(
            email = param.email,
            password = bCryptPasswordEncoder.encode(param.password),
            authorityLevel = param.authorityLevel ?: AuthorityLevel.USER
        )

        return userRepository.save(user)
    }

    fun getById(id: Long): User {
        return userRepository.findById(id)
            .orElseThrow { NotFoundUserException() }
    }

    fun getList(page: Int, size: Int): Page<User> {
        return userRepository.findAll(PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "id")))
    }

    fun signIn(param: SignInParam): User {
        val user = userRepository.findByEmail(param.email)
            .orElseThrow { NotFoundUserException() }

        val matches = bCryptPasswordEncoder.matches(param.password, user.password)
        return if (matches) user else throw NotFoundUserException()
    }

}
