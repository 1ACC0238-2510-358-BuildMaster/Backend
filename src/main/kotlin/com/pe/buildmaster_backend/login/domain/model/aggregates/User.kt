package com.pe.buildmaster_backend.login.domain.model.aggregates

import com.pe.buildmaster_backend.login.domain.model.valueobjects.Credential
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Favorite
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Profile
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Role
import java.util.UUID

data class User(
    val id: UUID = UUID.randomUUID(),
    var profile: Profile,
    private var credential: Credential,
    val favorites: MutableList<Favorite> = mutableListOf(),
    var role: Role
) {
    fun getCredential(): Credential = credential

    fun verifyCredentials(password: String): Boolean {
        return credential.getHashPassword() == password
    }

    fun updateProfile(newProfile: Profile) {
        this.profile = newProfile
    }

    fun updateRole(newRole: Role) {
        this.role = newRole
    }
}