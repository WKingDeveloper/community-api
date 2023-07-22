package com.wkd.communityapi.service.Post

import com.wkd.communityapi.exception.NotFoundBoardException
import com.wkd.communityapi.exception.NotFoundPostException
import com.wkd.communityapi.model.post.PostCreateParam
import com.wkd.communityapi.service.post.PostService
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@Transactional
@SpringBootTest(properties = ["spring.config.location=classpath:application-test.yml"])
class PostServiceTest @Autowired constructor(
    private val service: PostService,
) {

    @CsvSource(
        value = ["3,글 추가1,글 내용1,6", "4,글 추가2,글 내용2,7", "5,글 추가3,글 내용3,6"]
    )
    @ParameterizedTest
    fun create(id: Long, title: String, content: String, boardId: Long) {
        val param = PostCreateParam(
            title = title,
            content = content,
            boardId = boardId
        )

        val result = service.create(param)

        assertEquals(id, result.id)
        assertEquals(title, result.title)
        assertEquals(content, result.content)
        assertEquals(boardId, result.board.id)
    }

    @Test
    fun `create failed - NotFound Board Id`() {
        assertThrows(NotFoundBoardException::class.java) {
            service.create(
                PostCreateParam(
                    title = "제목",
                    content = "내용",
                    boardId = 10L
                )
            )
        }
    }

    @Test
    fun get() {
        val result = service.get(1L)
        assertEquals(1L, result.id)
        assertEquals("글 제목", result.title)
        assertEquals("글 내용", result.content)
        assertEquals(6, result.board.id)
    }

    @Test
    fun `get failed - NotFound Id`() {
        assertThrows(NotFoundPostException::class.java) { service.get(10L) }
    }

    @Test
    fun getList() {
        val result = service.getList(1, 10)
        assertEquals(2, result.content.size)
        assertEquals(2, result.totalElements)
    }

}
