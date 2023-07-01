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
    var id: Long = 0,

    @Column(nullable = false, length = 16)
    var name: String = "",

    var parentBoardId: Long? = null,

    @Column(nullable = false)
    var displayOrder: Int = 0,

    @Column(nullable = false)
    var status: Int = 0,

    var deletedAt: Timestamp? = null,

    @OneToMany(mappedBy = "board")
    var posts: List<Post> = emptyList()

) : EntityBase() {
}