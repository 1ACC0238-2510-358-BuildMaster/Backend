package com.pe.buildmaster_backend.community.infrastructure.persistence.jpa.entities

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "posts")
data class PostJpaEntity(
    @Id
    var id: UUID, // Coincide con el tipo de PostId.value

    @Column(nullable = false)
    var authorId: UUID, // Coincide con el tipo de UserId.value

    @Lob // Para textos largos
    @Column(nullable = false, columnDefinition = "TEXT")
    var content: String,

    // Para una lista de FileContent, la estrategia de mapeo puede variar.
    // Opción 1: ElementCollection (simple, pero con limitaciones)
    // Opción 2: Entidad separada FileAttachmentJpaEntity con relación @OneToMany
    // Vamos con ElementCollection por simplicidad para FileContent como Embeddable.
    @ElementCollection(fetch = FetchType.EAGER) // EAGER o LAZY según necesidad
    @CollectionTable(name = "post_media_attachments", joinColumns = [JoinColumn(name = "post_id")])
    @AttributeOverrides(
        AttributeOverride(name = "fileName", column = Column(name = "file_name")),
        AttributeOverride(name = "fileType", column = Column(name = "file_type")),
        AttributeOverride(name = "contentUrl", column = Column(name = "content_url"))
    )
    var mediaAttachments: List<FileContentEmbeddable> = emptyList(),

    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime,

    @Column(nullable = false)
    var updatedAt: LocalDateTime
)

@Embeddable
data class FileContentEmbeddable(
    @Column(name = "file_name")
    var fileName: String = "",
    @Column(name = "file_type")
    var fileType: String = "",
    @Column(name = "content_url", length = 2048) // URL puede ser larga
    var contentUrl: String = ""
) {
    // Constructor sin argumentos requerido por JPA para Embeddables si hay otros constructores.
    // Kotlin data classes con valores por defecto en todos los params no lo necesitan explícitamente.
}