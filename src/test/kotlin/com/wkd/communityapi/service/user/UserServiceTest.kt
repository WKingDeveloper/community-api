package com.wkd.communityapi.service.user

import com.wkd.communityapi.model.auth.AuthorityLevel
import com.wkd.communityapi.model.user.UserCreateParam
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@Transactional
@SpringBootTest(properties = ["spring.config.location=classpath:application-test.yml"])
class UserServiceTest @Autowired constructor(
    private val service: UserService,
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

        val result = service.create(param)

        assertEquals(id, result.id)
        assertEquals(email, result.email)
        assertEquals(password, result.password)
        assertEquals(AuthorityLevel.valueOf(authorityLevel), result.authorityLevel)
    }

    @Test
    fun get() {
        val result = service.getById(1L)
        assertEquals(1L, result.id)
        assertEquals("user@wkd.com", result.email)
        assertEquals("user12!@", result.password)
        assertEquals(AuthorityLevel.USER, result.authorityLevel)
    }

}
