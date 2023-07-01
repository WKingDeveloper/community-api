package com.wkd.communityapi.model.post

import com.wkd.communityapi.model.board.Board
import jakarta.persistence.*
import org.hibernate.annotations.Where
import java.sql.Timestamp

@Entity
@Where(clause = "status > 0")
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, length = 512)
    var title: String = "",

    @Column(nullable = false, columnDefinition = "TEXT")
    var content: String = "",

    @Column(nullable = false, columnDefinition = "INT UNSIGNED DEFAULT 0")
    var likes: Int = 0,

    @Column(nullable = false, columnDefinition = "INT UNSIGNED DEFAULT 0")
    var views: Int = 0,

    @Column(nullable = false)
    var status: Int = 0,

    var deletedAt: Timestamp? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId", referencedColumnName = "id", insertable = false, updatable = false)
    var board: Board? = null
)