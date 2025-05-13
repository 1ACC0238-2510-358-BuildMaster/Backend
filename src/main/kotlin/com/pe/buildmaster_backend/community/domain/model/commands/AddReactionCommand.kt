package com.pe.buildmaster_backend.community.domain.model.commands

import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId
import com.pe.buildmaster_backend.community.domain.model.valueobjects.ReactionType
import com.pe.buildmaster_backend.community.domain.model.valueobjects.UserId

data class AddReactionCommand(
    val postId: PostId,
    val userId: UserId,
    val reactionType: ReactionType
)