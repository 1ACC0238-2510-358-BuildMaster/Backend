package com.pe.buildmaster_backend.technical_configuration.domain.model.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "builds")
data class Build(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ElementCollection
    @CollectionTable(name = "build_components", joinColumns = [JoinColumn(name = "build_id")])
    @Column(name = "component_id")
    val componentIds: List<Long> = emptyList(),

    val createdAt: LocalDateTime = LocalDateTime.now()
)
