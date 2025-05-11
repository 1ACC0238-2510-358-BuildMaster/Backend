package com.pe.buildmaster_backend.community.interfaces.rest.mappers

import com.pe.buildmaster_backend.community.domain.model.commands.AddReactionCommand
import com.pe.buildmaster_backend.community.domain.model.entities.Reaction
import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId
import com.pe.buildmaster_backend.community.interfaces.rest.resources.AddReactionRequest
import com.pe.buildmaster_backend.community.interfaces.rest.resources.ReactionResponse

object ReactionResourceMapper {

    fun toAddReactionCommand(request: AddReactionRequest, postId: PostId, userId: UserId): AddReactionCommand {
        return AddReactionCommand(
            postId = postId,
            userId = userId,
            reactionType = request.reactionType
        )
    }

    fun toResponse(domainReaction: Reaction): ReactionResponse {
        return ReactionResponse(
            id = domainReaction.id, // UUID de la reacción
            postId = domainReaction.postId.value,
            userId = domainReaction.userId.value,
            type = domainReaction.type,
            createdAt = domainReaction.createdAt
        )
    }
}