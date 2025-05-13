package com.pe.buildmaster_backend.login.interfaces.rest.resources

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtProvider {
    private val secretKey = "yourSecretKey" // Replace with a secure key
    private val expirationTime = 86400000L // 1 day in milliseconds

    fun generarToken(userId: String, perfil: String): String {
        val claims = mapOf("perfil" to perfil)
        return Jwts.builder()
            .setSubject(userId)
            .addClaims(claims)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expirationTime))
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact()
    }
}