package com.pe.buildmaster_backend.community.domain.interfaces

import com.pe.buildmaster_backend.community.domain.model.entities.Comment
import com.pe.buildmaster_backend.community.domain.model.valueobjects.CommentId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId

interface CommentRepository {
    suspend fun findById(id: CommentId): Comment?
    suspend fun save(comment: Comment): Comment
    suspend fun delete(comment: Comment)
    suspend fun findByPostId(postId: PostId): List<Comment>
    suspend fun findByAuthorId(authorId: UserId): List<Comment>
}