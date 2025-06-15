package com.pe.buildmaster_backend.login.domain.model.valueobjects

import java.util.*

data class User(
    val id: UUID = UUID.randomUUID(),
    var profile: Profile,
    private var credential: Credential,
    val favorites: MutableList<Favorite> = mutableListOf()
) {
    fun getCredential(): Credential = credential

    fun verifyCredentials(password: String): Boolean {
        return credential.verify(password)
    }

    fun updateProfile(newProfile: Profile) {
        this.profile = newProfile
    }

    fun addFavorite(favorite: Favorite) {
        favorites.add(favorite)
    }
}