package com.wkd.communityapi.service.post

import com.wkd.communityapi.model.post.Post
import com.wkd.communityapi.model.post.PostCreateParam
import com.wkd.communityapi.repository.board.BoardRepository
import com.wkd.communityapi.repository.post.PostRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class PostService(
    private val repository: PostRepository,
    private val boardRepository: BoardRepository
) {

    fun create(param: PostCreateParam): Post {
        // todo : Exception 처리(하위 자식 board만 가능하도록)
        val board =
            boardRepository.findById(param.boardId)
                .orElseThrow { RuntimeException("Board not found with id: ${param.boardId}") }


        val post = Post(
            title = param.title,
            content = param.content,
            board = board
        )

        return repository.save(post)
    }

//    fun get(id: Long): Post {
//        return repository.findById(id)
//            .orElseThrow { RuntimeException("Post not found with id: $id") }
//    }
//
//    fun getList(page: Int, size: Int): Page<Post> {
//        return repository.findAll(PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "id")))
//    }
}
