package com.wkd.communityapi.service.board

import com.wkd.communityapi.model.board.BoardParam
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(properties = ["spring.config.location=classpath:application-test.yml"])
//@DataJpaTest(properties = ["spring.config.location=classpath:application-test.yml"])
class BoardServiceTest @Autowired constructor(
    private val service: BoardService,
) {

    @CsvSource(
        value = ["8,정보,0,0", "9,질문답변,0,8", "10,꿀팁,1,8"]
    )
    @ParameterizedTest
    fun create(id: Long, name: String, parentBoardId: Long, displayOrder: Int) {
        val param = BoardParam(
            name = name,
            parentBoardId = if (parentBoardId == 0L) null else parentBoardId,
            displayOrder = displayOrder
        )

        val result = service.create(param)

        assertEquals(id, result.id)
        assertEquals(name, result.name)
        assertEquals(if (parentBoardId == 0L) null else parentBoardId, result.parentBoardId)
        assertEquals(displayOrder, result.displayOrder)
    }

    @Test
    fun get() {
        val result = service.get(1L)
        assertEquals(1L, result.id)
        assertEquals("지식", result.name)
        assertEquals(null, result.parentBoardId)
        assertEquals(0, result.displayOrder)
    }

}
