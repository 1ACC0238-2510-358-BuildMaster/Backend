package com.pe.buildmaster_backend.catalogue.domain.model.entities


import com.pe.buildmaster_backend.catalogue.domain.model.commands.CreateComponentCommand
import com.pe.buildmaster_backend.catalogue.domain.model.valueobjects.Specifications
import jakarta.persistence.*
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Entity
@Table(name = "components")
@Getter
@Setter
@NoArgsConstructor
class Component(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,
    val type: String,
    val price: Double,

    @Embedded
    val specifications: Specifications,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    val category: Category,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    val manufacturer: Manufacturer
){
    companion object {
        fun from(command: CreateComponentCommand, category: Category, manufacturer: Manufacturer): Component {
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
}
