package com.wkd.communityapi.model.board

import com.wkd.communityapi.model.common.EntityBase
import com.wkd.communityapi.model.post.Post
import jakarta.persistence.*
import org.hibernate.annotations.Where
import java.sql.Timestamp

@Entity
@Where(clause = "status > 0")
class Board(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 16)
    val name: String = "",

    val parentBoardId: Long? = null,

    @Column(nullable = false)
    val displayOrder: Int = 0,

    @Column(nullable = false)
    val status: Int = 0,

    val deletedAt: Timestamp? = null,

    @OneToMany(mappedBy = "board")
    val posts: List<Post> = emptyList()

) : EntityBase() {
}