package com.pe.buildmaster_backend.catalogue.domain.model.entities

import jakarta.persistence.*

@Entity
@Table(name = "categories")
class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,

    @ManyToOne
    @JoinColumn(name = "parent_id")
    val parent: Category? = null
){
    // Constructor vacío necesario para JPA
    constructor() : this(0, "", null)
}