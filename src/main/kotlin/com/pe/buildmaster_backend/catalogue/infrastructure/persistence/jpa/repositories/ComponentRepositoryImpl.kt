package com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.catalogue.domain.model.entities.Component
import org.springframework.stereotype.Repository
import jakarta.persistence.*
import jakarta.persistence.criteria.Predicate
@Repository
class ComponentRepositoryImpl(
    private val jpaRepository: JpaComponentRepository,
    private val entityManager: EntityManager
) : ComponentRepository {

    override fun save(component: Component): Component = jpaRepository.save(component)

    override fun findById(id: Long): Component? = jpaRepository.findById(id).orElse(null)

    override fun findAll(): List<Component> = jpaRepository.findAll()

    override fun deleteById(id: Long) {
        jpaRepository.deleteById(id)
    }
    override fun search(
        name: String?,
        type: String?,
        categoryId: Long?,
        manufacturerId: Long?
    ): List<Component> {
        val cb = entityManager.criteriaBuilder
        val query = cb.createQuery(Component::class.java)
        val root = query.from(Component::class.java)

        val predicates = mutableListOf<Predicate>()

        name?.let {
            predicates.add(cb.like(cb.lower(root.get("name")), "%${it.lowercase()}%"))
        }
        type?.let {
            predicates.add(cb.equal(cb.lower(root.get("type")), it.lowercase()))
        }
        categoryId?.let {
            predicates.add(cb.equal(root.get<Long>("category").get<Long>("id"), it))
        }
        manufacturerId?.let {
            predicates.add(cb.equal(root.get<Long>("manufacturer").get<Long>("id"), it))
        }

        query.where(*predicates.toTypedArray())
        return entityManager.createQuery(query).resultList
    }
}
