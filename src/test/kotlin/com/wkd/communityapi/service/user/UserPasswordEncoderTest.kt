package com.wkd.communityapi.service.user

import com.wkd.communityapi.configuration.EncoderConfig
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class UserPasswordEncoderTest {
    private val bCrypt = EncoderConfig().encoder()


    @Test
    fun encode() {
        val pw = "abc1234"
        val encodePw = bCrypt.encode(pw)
        assertNotEquals(pw, encodePw)
    }

    @Test
    fun match() {
        val pw = "abc1234"
        val encodePw = bCrypt.encode(pw)

        val inputPW = "abc1234"
        val check: Boolean = bCrypt.matches(inputPW, encodePw)

        assertEquals(check, true)
    }

    @Test
    fun matchFailed() {
        val pw = "abc1234"
        val encodePw = bCrypt.encode(pw)

        val inputPW = "abc123"
        val check: Boolean = bCrypt.matches(inputPW, encodePw)

        assertEquals(check, false)
    }
}
