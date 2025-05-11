package com.pe.buildmaster_backend.community.domain.model.aggregates




import com.pe.buildmaster_backend.community.domain.model.entities.Comment
import com.pe.buildmaster_backend.community.domain.model.entities.Post
import com.pe.buildmaster_backend.community.domain.model.entities.Reaction
import com.pe.buildmaster_backend.community.domain.model.valueobjects.CommentId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.ReactionType
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId
import java.time.LocalDateTime


data class PostAggregate(
    val post: Post,
    val comments: MutableList<Comment> = mutableListOf(),
    val reactions: MutableList<Reaction> = mutableListOf()
) {
    fun addComment(authorId: UserId, content: String): Comment {
        val comment = Comment(
            id = CommentId(),
            postId = post.id,
            authorId = authorId,
            content = content,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        comments.add(comment)
        // Lógica adicional, notificaciones, etc.
        return comment
    }

    fun addReaction(userId: UserId, type: ReactionType): Reaction {
        // Lógica para evitar reacciones duplicadas del mismo tipo por el mismo usuario, o cambiar una existente
        reactions.removeAll { it.userId == userId && it.postId == post.id } // Ejemplo simple: quitar previas
        val reaction = Reaction(
            postId = post.id,
            userId = userId,
            type = type,
            createdAt = LocalDateTime.now()
        )
        reactions.add(reaction)
        // Lógica adicional
        return reaction
    }


}


