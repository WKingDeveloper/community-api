package com.wkd.communityapi.model.user

import com.wkd.communityapi.model.auth.AuthorityLevel
import com.wkd.communityapi.model.common.CommonState
import com.wkd.communityapi.model.common.EntityBase
import jakarta.persistence.*

@Entity
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 512)
    val email: String,

    @Column(nullable = false, length = 512)
    val password: String,

    @Column(nullable = false, length = 64)
    @Enumerated(EnumType.STRING)
    var authorityLevel: AuthorityLevel,

    @Column(nullable = false)
    val status: CommonState = CommonState.ACTIVE,

    ) : EntityBase()
