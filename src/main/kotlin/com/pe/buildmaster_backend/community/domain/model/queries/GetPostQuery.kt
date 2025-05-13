package com.pe.buildmaster_backend.community.domain.model.queries

import com.pe.buildmaster_backend.community.domain.model.valueobjects.PostId

data class GetPostQuery(
    val postId: PostId
)