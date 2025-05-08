package com.pe.buildmaster_backend.catalogue.domain.model.entities

import jakarta.persistence.*

@Entity
@Table(name = "manufacturers")
class Manufacturer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,
    val website: String,
    val supportEmail: String
){
    // Constructor vacío necesario para JPA
    constructor() : this(0, "", "", "")
}

