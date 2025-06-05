package com.pe.buildmaster_backend.catalogue.domain.model.entities


import com.pe.buildmaster_backend.catalogue.domain.model.commands.CreateComponentCommand
import com.pe.buildmaster_backend.catalogue.domain.model.valueobjects.Specifications
import jakarta.persistence.*
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.util.Optional

@Entity
@Table(name = "components")
@Getter
@Setter
@NoArgsConstructor(force = true) // Constructor sin parámetros
class Component(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0, // El valor predeterminado de 'id' para permitir la creación

    var name: String = "", // Asignación de valores predeterminados para facilitar el uso sin parámetros
    var type: String = "",
    var price: Double = 0.0,

    @Embedded
    var specifications: Specifications = Specifications("", "", 0, ""), // Constructor por defecto de Specifications

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    var category: Category? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    var manufacturer: Manufacturer? = null

) {
    companion object {
        fun from(command: CreateComponentCommand, category: Category?, manufacturer: Manufacturer?): Component {
            return Component(
                name = command.name,
                type = command.type,
                price = command.price,
                specifications = command.specifications,
                category = category,
                manufacturer = manufacturer
            )
        }
    }

    fun updateWith(command: CreateComponentCommand, category: Category?, manufacturer: Manufacturer?): Component {
        return Component(
            id = this.id,
            name = command.name,
            type = command.type,
            price = command.price,
            specifications = command.specifications,
            category = category,
            manufacturer = manufacturer
        )
    }
}