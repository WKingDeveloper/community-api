package com.wkd.communityapi.service.user

import com.wkd.communityapi.exception.NotFoundUserException
import com.wkd.communityapi.model.auth.AuthorityLevel
import com.wkd.communityapi.model.user.SignInParam
import com.wkd.communityapi.model.user.SignUpParam
import jakarta.transaction.Transactional
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.annotation.Rollback

@TestMethodOrder(value = MethodOrderer.OrderAnnotation::class)
@SpringBootTest(properties = ["spring.config.location=classpath:application-test.yml"])
class UserServiceTest @Autowired constructor(
    private val userService: UserService,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
) {

    @CsvSource(
        value = ["1,user2@wkd.com,user2!!,USER", "2,admin@wkd.com,admin!!,ADMIN"]
    )
    @ParameterizedTest
    @Transactional
    @Rollback(false)
    @Order(1)
    fun signUp(id: Long, email: String, password: String, authorityLevel: String) {
        val param = SignUpParam(
            email = email,
            password = password,
            authorityLevel = AuthorityLevel.valueOf(authorityLevel)
        )

        val result = userService.signUp(param)

        assertEquals(id, result.id)
        assertEquals(email, result.email)
        assertEquals(bCryptPasswordEncoder.matches(password, result.password), true)
        assertEquals(AuthorityLevel.valueOf(authorityLevel), result.authorityLevel)
    }

    @Test
    @Order(2)
    fun getById() {
        val result = userService.getById(1L)

        assertEquals(1L, result.id)
        assertEquals("user2@wkd.com", result.email)
        assertEquals(AuthorityLevel.USER, result.authorityLevel)
    }

    @Test
    @Order(3)
    fun `getById failed - NotFound Id`() {
        Assertions.assertThrows(NotFoundUserException::class.java) { userService.getById(3L) }
    }

    @Test
    @Order(4)
    fun getList() {
        val result = userService.getList(1, 10)
        assertEquals(2, result.content.size)
        assertEquals(2, result.totalElements)
    }

    @Test
    @Order(5)
    fun `signIn success`() {
        val param = SignInParam(
            email = "user2@wkd.com",
            password = "user2!!"
        )

        val user = userService.signIn(param)
        assertEquals(1, user.id)
    }

    @Test
    @Order(6)
    fun `signIn failed - wrong email`() {
        val param = SignInParam(
            email = "user23@wkd.com",
            password = "user2!!"
        )
        Assertions.assertThrows(NotFoundUserException::class.java) { userService.signIn(param) }
    }

    @Test
    @Order(7)
    fun `signIn failed - wrong password`() {
        val param = SignInParam(
            email = "user2@wkd.com",
            password = "user2!@"
        )
        Assertions.assertThrows(NotFoundUserException::class.java) { userService.signIn(param) }
    }
}
