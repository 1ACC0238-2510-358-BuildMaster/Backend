package com.pe.buildmaster_backend.login.domain.exceptions

class UserNotFoundException(userId: String) : RuntimeException("User with ID $userId not found")