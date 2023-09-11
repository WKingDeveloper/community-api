package com.wkd.communityapi.service.board

import com.wkd.communityapi.exception.NotFoundBoardException
import com.wkd.communityapi.model.board.BoardCreateParam
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(properties = ["spring.config.location=classpath:application-test.yml"])
class BoardServiceTest @Autowired constructor(
    private val boardService: BoardService
) {

    @CsvSource(
        value = ["8,정보,0,0", "9,질문답변,0,8", "10,꿀팁,1,8"]
    )
    @ParameterizedTest
    @Transactional
    fun create(id: Long, name: String, parentBoardId: Long, indexNo: Int) {
        val param = BoardCreateParam(
            name = name,
            parentBoardId = if (parentBoardId == 0L) null else parentBoardId,
            indexNo = indexNo
        )

        val result = boardService.create(param)

        assertEquals(id, result.id)
        assertEquals(name, result.name)
        assertEquals(if (parentBoardId == 0L) null else parentBoardId, result.parentBoardId)
        assertEquals(indexNo, result.indexNo)
    }

    @Test
    fun get() {
        val result = boardService.get(1L)
        assertEquals(1L, result.id)
        assertEquals("지식", result.name)
        assertEquals(null, result.parentBoardId)
        assertEquals(0, result.indexNo)
    }

    @Test
    fun `get failed - NotFound Id`() {
        assertThrows(NotFoundBoardException::class.java) { boardService.get(10L) }
    }

    @Test
    fun getList() {
        val result = boardService.getList()
        assertEquals(5, result.size)
        assertEquals(2, result[0].childBoards.size)
    }

}
