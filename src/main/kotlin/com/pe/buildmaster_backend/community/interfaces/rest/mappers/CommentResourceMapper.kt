package com.pe.buildmaster_backend.community.interfaces.rest.mappers

import com.pe.buildmaster_backend.community.domain.model.commands.AddCommentCommand
import com.pe.buildmaster_backend.community.domain.model.entities.Comment
import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId
import com.pe.buildmaster_backend.community.interfaces.rest.resources.AddCommentRequest
import com.pe.buildmaster_backend.community.interfaces.rest.resources.CommentResponse

object CommentResourceMapper {

    fun toAddCommentCommand(request: AddCommentRequest, postId: PostId, authorId: UserId): AddCommentCommand {
        return AddCommentCommand(
            postId = postId,
            authorId = authorId,
            content = request.content
        )
    }

    fun toResponse(domainComment: Comment): CommentResponse {
        return CommentResponse(
            id = domainComment.id.value,
            postId = domainComment.postId.value,
            authorId = domainComment.authorId.value,
            content = domainComment.content,
            createdAt = domainComment.createdAt,
            updatedAt = domainComment.updatedAt
        )
    }
}