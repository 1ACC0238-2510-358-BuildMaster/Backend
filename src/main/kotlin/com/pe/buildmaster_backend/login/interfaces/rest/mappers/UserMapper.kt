package com.pe.buildmaster_backend.login.interfaces.rest.mappers

import com.pe.buildmaster_backend.login.domain.model.entities.UserEntity
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Credential
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Name
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Profile
import com.pe.buildmaster_backend.login.domain.model.aggregates.User
import java.util.UUID

object UserMapper {

    fun User.toEntity(): UserEntity = UserEntity(
        id = null, // Set to null if the ID is auto-generated
        email = this.getCredential().email,
        passwordHash = this.getCredential().getHashPassword(),
        name = this.profile.name.value,
        biografy = this.profile.biografy,
        fotoUrl = this.profile.fotoUrl,
        role = this.role
    )

    fun UserEntity.toDomain(): User = User(
        id = this.id?.toString()?.let { UUID.fromString(it) } ?: UUID.randomUUID(), // Handle null case with a default UUID
        profile = Profile(Name(this.name), this.biografy, this.fotoUrl),
        credential = Credential(this.email, this.passwordHash),
        role = this.role
    )
}