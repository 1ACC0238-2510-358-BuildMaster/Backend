package com.pe.buildmaster_backend.technical_configuration.domain.model.aggregates

import com.pe.buildmaster_backend.catalogue.domain.model.entities.Component

class BuildAggregate private constructor(
    val components: List<Component>
) {
    init {
        // CPU
        val cpus = components.count { it.type == "CPU" }
        require(cpus == 1) { "Debes tener exactamente 1 CPU." }

        // GPU
        val gpus = components.count { it.type == "GPU" }
        require(gpus >= 1) { "Debes tener al menos 1 GPU." }
        require(gpus <= 4) { "No puedes tener más de 4 GPU." }

        // Motherboard
        val motherboards = components.count { it.type == "Motherboard" }
        require(motherboards == 1) { "Debes tener exactamente 1 Motherboard." }

        // RAM
        val rams = components.count { it.type == "RAM" }
        require(rams >= 1) { "Debes tener al menos 1 módulo de RAM." }
        require(rams <= 8) { "No puedes tener más de 8 módulos de RAM." }

        // Storage
        val storages = components.count { it.type == "Storage" }
        require(storages >= 1) { "Debes tener al menos 1 unidad de almacenamiento." }
        require(storages <= 6) { "No puedes tener más de 6 unidades de almacenamiento." }

        // Power Supply
        val psus = components.count { it.type == "PSU" }
        require(psus == 1) { "Debes tener exactamente 1 PSU." }

        // Case
        val cases = components.count { it.type == "Case" }
        require(cases == 1) { "Debes tener exactamente 1 Case." }

        // Cooling System (Opcional)
        val coolers = components.count { it.type == "Cooler" }
        require(coolers <= 5) { "No puedes tener más de 5 sistemas de enfriamiento." }
    }

    companion object {
        fun create(components: List<Component>): BuildAggregate {
            return BuildAggregate(components)
        }
    }
}
