package com.pe.buildmaster_backend.community.domain.model.commands

import com.pe.buildmaster_backend.community.domain.model.valueobjects.FileContent
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId

data class CreatePostCommand(
    val authorId: UserId,
    val content: String,
    val mediaAttachments: List<FileContent> = emptyList()
)