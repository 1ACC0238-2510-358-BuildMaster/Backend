package com.pe.buildmaster_backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BuildMasterBackendApplication

fun main(args: Array<String>) {
	runApplication<BuildMasterBackendApplication>(*args)
}
