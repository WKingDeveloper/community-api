package com.wkd.communityapi.service.user

import com.wkd.communityapi.exception.NotFoundUserException
import com.wkd.communityapi.model.auth.AuthorityLevel
import com.wkd.communityapi.model.user.UserCreateParam
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@Transactional
@SpringBootTest(properties = ["spring.config.location=classpath:application-test.yml"])
class UserServiceTest @Autowired constructor(
    private val userService: UserService
) {

    @CsvSource(
        value = ["3,user2@wkd.com,user2!!,USER", "4,admin@wkd.com,admin!!,ADMIN"]
    )
    @ParameterizedTest
    fun create(id: Long, email: String, password: String, authorityLevel: String) {
        val param = UserCreateParam(
            email = email,
            password = password,
            authorityLevel = AuthorityLevel.valueOf(authorityLevel)
        )

        val result = userService.create(param)

        assertEquals(id, result.id)
        assertEquals(email, result.email)
        assertEquals(password, result.password)
        assertEquals(AuthorityLevel.valueOf(authorityLevel), result.authorityLevel)
    }

    @Test
    fun getById() {
        val result = userService.getById(1L)
        assertEquals(1L, result.id)
        assertEquals("user@wkd.com", result.email)
        assertEquals("user12!@", result.password)
        assertEquals(AuthorityLevel.USER, result.authorityLevel)
    }

    @Test
    fun `getById failed - NotFound Id`() {
        Assertions.assertThrows(NotFoundUserException::class.java) { userService.getById(3L) }
    }

    @Test
    fun getList() {
        val result = userService.getList(1, 10)
        assertEquals(2, result.content.size)
        assertEquals(2, result.totalElements)
    }
}
