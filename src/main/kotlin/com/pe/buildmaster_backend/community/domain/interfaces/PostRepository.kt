package com.pe.buildmaster_backend.community.domain.interfaces

import com.pe.buildmaster_backend.community.domain.model.entities.Post
import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId

interface PostRepository {
    suspend fun findById(id: PostId): Post?
    suspend fun save(post: Post): Post
    suspend fun delete(post: Post)
    suspend fun findByAuthorId(authorId: UserId): List<Post>
    // Podríamos añadir más métodos, como paginación, búsqueda por tags, etc.
    // suspend fun findAll(page: Int, size: Int): List<Post>
}