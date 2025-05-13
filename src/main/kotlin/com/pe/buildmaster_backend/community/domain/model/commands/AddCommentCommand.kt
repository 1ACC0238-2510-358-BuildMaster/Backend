package com.pe.buildmaster_backend.community.domain.model.commands

import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId

data class AddCommentCommand(
    val postId: PostId,
    val authorId: UserId,
    val content: String
)