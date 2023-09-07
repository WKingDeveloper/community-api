package com.wkd.communityapi.service.post

import com.wkd.communityapi.exception.BadRequestPostCreateException
import com.wkd.communityapi.exception.NotFoundBoardException
import com.wkd.communityapi.exception.NotFoundPostException
import com.wkd.communityapi.model.post.Post
import com.wkd.communityapi.model.post.PostCreateParam
import com.wkd.communityapi.repository.board.BoardRepository
import com.wkd.communityapi.repository.post.PostRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository,
    private val boardRepository: BoardRepository
) {

    @Transactional
    fun create(param: PostCreateParam): Post {
        val board =
            boardRepository.findById(param.boardId)
                .orElseThrow { NotFoundBoardException() }

        if (board.parentBoardId == null) throw BadRequestPostCreateException()

        val post = Post(
            title = param.title,
            content = param.content,
            board = board
        )

        return postRepository.save(post)
    }

    fun get(id: Long): Post {
        return postRepository.findById(id)
            .orElseThrow { NotFoundPostException() }
    }

    fun getList(page: Int, size: Int): Page<Post> {
        return postRepository.findAll(PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "id")))
    }
}
