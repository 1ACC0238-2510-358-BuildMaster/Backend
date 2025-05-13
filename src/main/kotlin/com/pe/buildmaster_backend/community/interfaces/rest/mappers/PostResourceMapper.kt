package com.pe.buildmaster_backend.community.interfaces.rest.mappers

import com.pe.buildmaster_backend.community.domain.model.commands.CreatePostCommand
import com.pe.buildmaster_backend.community.domain.model.entities.Post
import com.pe.buildmaster_backend.community.domain.model.valueobjects.FileContent
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId
import com.pe.buildmaster_backend.community.interfaces.rest.resources.CreatePostRequest
import com.pe.buildmaster_backend.community.interfaces.rest.resources.FileContentResource
import com.pe.buildmaster_backend.community.interfaces.rest.resources.PostResponse

object PostResourceMapper {

    fun toCreatePostCommand(request: CreatePostRequest, authorId: UserId): CreatePostCommand {
        return CreatePostCommand(
            authorId = authorId,
            content = request.content,
            mediaAttachments = request.mediaAttachments.map {
                FileContent(
                    fileName = it.fileName,
                    fileType = it.fileType,
                    contentUrl = it.contentUrl
                )
            }
        )
    }

    // Para los conteos, necesitaríamos acceso a los servicios o que el PostResponse se enriquezca después
    fun toResponse(
        domainPost: Post,
        commentCount: Int = 0, // Estos deberían ser calculados y pasados
        likeCount: Int = 0,
        dislikeCount: Int = 0,
        repostCount: Int = 0
    ): PostResponse {
        return PostResponse(
            id = domainPost.id.value,
            authorId = domainPost.authorId.value,
            content = domainPost.content,
            mediaAttachments = domainPost.mediaAttachments.map {
                FileContentResource(
                    fileName = it.fileName,
                    fileType = it.fileType,
                    contentUrl = it.contentUrl
                )
            },
            createdAt = domainPost.createdAt,
            updatedAt = domainPost.updatedAt,
            commentCount = commentCount,
            likeCount = likeCount,
            dislikeCount = dislikeCount,
            repostCount = repostCount
        )
    }

    fun toFileContent(resource: FileContentResource): FileContent {
        return FileContent(
            fileName = resource.fileName,
            fileType = resource.fileType,
            contentUrl = resource.contentUrl
        )
    }

    fun toFileContentResource(domain: FileContent): FileContentResource {
        return FileContentResource(
            fileName = domain.fileName,
            fileType = domain.fileType,
            contentUrl = domain.contentUrl
        )
    }
}