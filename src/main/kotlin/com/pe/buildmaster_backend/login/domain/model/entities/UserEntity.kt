package com.pe.buildmaster_backend.login.domain.model.entities

<<<<<<< Updated upstream
import jakarta.persistence.Entity
import jakarta.persistence.Id
=======
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Role
import jakarta.persistence.*
>>>>>>> Stashed changes
import java.util.*

@Entity
@Table(name = "users")
data class UserEntity(
<<<<<<< Updated upstream
    @Id val id: UUID,
    val email: String,
    val passwordHash: String,
    val name: String,
    val biografy: String?,
    val fotoUrl: String?
)
=======
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
>>>>>>> Stashed changes
