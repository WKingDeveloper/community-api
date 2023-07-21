package com.wkd.communityapi.model.post

data class PostCreateParam(
    val title: String,

    val content: String,

    val boardId: Long
)
