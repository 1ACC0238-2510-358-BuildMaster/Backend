package com.pe.buildmaster_backend.login.interfaces.rest.mappers

import com.pe.buildmaster_backend.login.domain.model.entities.UserEntity
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Credential
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Name
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Profile
import com.pe.buildmaster_backend.login.domain.model.valueobjects.User

object UserMapper {
    fun User.toEntity(): UserEntity = UserEntity(
        id = this.id,
        email = this.credenciales.email,
        passwordHash = this.credenciales.hashPassword,
        name = this.perfil.nombre.value,
        biografy = this.perfil.biografia,
        fotoUrl = this.perfil.fotoUrl
    )

    fun UserEntity.toDomain(): User = User(
        id = this.id,
        perfil = Profile(Name(this.name), this.biografy, this.fotoUrl),
        credenciales = Credential(this.email, this.passwordHash)
    )
}