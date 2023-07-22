package com.wkd.communityapi.model.post

import com.wkd.communityapi.model.board.Board
import com.wkd.communityapi.model.common.CommonState
import jakarta.persistence.*
import org.hibernate.annotations.Where
import java.sql.Timestamp

@Entity
@Where(clause = "status > 0")
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 512)
    val title: String = "",

    @Column(nullable = false, columnDefinition = "TEXT")
    val content: String = "",

    @Column(nullable = false, columnDefinition = "INT UNSIGNED DEFAULT 0")
    val likes: Int = 0,

    @Column(nullable = false, columnDefinition = "INT UNSIGNED DEFAULT 0")
    val views: Int = 0,

    @Column(nullable = false)
    val status: CommonState = CommonState.ACTIVE,

    val deletedAt: Timestamp? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    val board: Board
)