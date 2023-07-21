package com.wkd.communityapi.model.board

import com.wkd.communityapi.model.common.CommonState
import com.wkd.communityapi.model.common.EntityBase
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

    @Column
    val parentBoardId: Long? = null,

    @Column(nullable = false)
    val indexNo: Int = 0,

    @Column(nullable = false)
    val status: CommonState = CommonState.ACTIVE,

    @Column
    val deletedAt: Timestamp? = null,


    ) : EntityBase()
