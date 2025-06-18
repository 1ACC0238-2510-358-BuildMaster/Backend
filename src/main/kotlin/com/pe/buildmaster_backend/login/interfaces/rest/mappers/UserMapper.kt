package com.pe.buildmaster_backend.login.interfaces.rest.mappers

import com.pe.buildmaster_backend.login.domain.model.entities.UserEntity
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Credential
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Name
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Profile
<<<<<<< Updated upstream
import com.pe.buildmaster_backend.login.domain.model.valueobjects.User
=======
import com.pe.buildmaster_backend.login.domain.model.aggregates.User
import java.util.UUID
>>>>>>> Stashed changes

object UserMapper {


    fun User.toEntity(): UserEntity = UserEntity(
<<<<<<< Updated upstream
        id = this.id,
        email = this.credenciales.email,
        passwordHash = this.credenciales.hashPassword,
        name = this.perfil.name.value,
        biografy = this.perfil.biografy,
        fotoUrl = this.perfil.fotoUrl
    )

    fun UserEntity.toDomain(): User = User(
        id = this.id,
        perfil = Profile(Name(this.name), this.biografy, this.fotoUrl),
        credenciales = Credential(this.email, this.passwordHash),
=======
        id = null, // Set to null if the ID is auto-generated
        email = this.getCredential().email,
        passwordHash = this.getCredential().getHashPassword(),
        name = this.profile.name.value,
        biografy = this.profile.biografy,
        fotoUrl = this.profile.fotoUrl,
        role = this.role // Ensure Role is correctly mapped
    )

    fun UserEntity.toDomain(): User = User(
        id = this.id?.toString()?.let { UUID.fromString(it) } ?: UUID.randomUUID(),
        profile = Profile(Name(this.name), this.biografy, this.fotoUrl),
        credential = Credential(this.email, this.passwordHash),
        role = this.role
>>>>>>> Stashed changes
    )
}