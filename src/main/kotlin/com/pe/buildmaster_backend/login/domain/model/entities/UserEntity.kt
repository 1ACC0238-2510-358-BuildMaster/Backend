package com.pe.buildmaster_backend.login.domain.model.entities

import com.pe.buildmaster_backend.login.domain.model.valueobjects.Role
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var email: String,

    @Column(nullable = false)
    var passwordHash: String,

    @Column(nullable = false)
    var name: String,

    @Column
    var biografy: String? = null,

    @Column
    var fotoUrl: String? = null,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var role: Role
) {
    constructor() : this(
        id = null,
        email = "",
        passwordHash = "",
        name = "",
        biografy = null,
        fotoUrl = null,
        role = Role.USER
    )
}