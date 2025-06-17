package com.pe.buildmaster_backend.login.interfaces.rest.mappers

import com.pe.buildmaster_backend.login.domain.model.entities.UserEntity
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Credential
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Name
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Profile
import com.pe.buildmaster_backend.login.domain.model.aggregates.User

object UserMapper {


    fun User.toEntity(): UserEntity = UserEntity(
        id = this.id,
        email = this.getCredential().email,
        passwordHash = this.getCredential().getHashPassword(),
        name = this.profile.name.value,
        biografy = this.profile.biografy,
        fotoUrl = this.profile.fotoUrl,
        role = this.role// Convert Role to String
    )

    fun UserEntity.toDomain(): User = User(
        id = this.id,
        profile = Profile(Name(this.name), this.biografy, this.fotoUrl),
        credential = Credential(this.email, this.passwordHash),
        role = this.role
    )
}