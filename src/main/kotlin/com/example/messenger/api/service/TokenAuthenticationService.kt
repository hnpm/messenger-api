package com.example.messenger.api.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

object TokenAuthenticationService {
    private val TOKEN_EXPIRY: Long = 864000000
    private val SECRET = "$78gr43g7g8feb8we"
    private val TOKEN_PREFIX = "Bearer"
    private val AUTHORIZATION_HEADER_KEY = "Authorization"

    fun addAuthentication(response: HttpServletResponse, username: String) {
        val jwt = Jwts.builder()
            .setSubject(username)
            .setExpiration(Date((System.currentTimeMillis() + TOKEN_EXPIRY)))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact()
        response.addHeader(AUTHORIZATION_HEADER_KEY, "$TOKEN_PREFIX $jwt")
    }

    fun getAuthentication(request: HttpServletRequest): Authentication? {
        val token = request.getHeader(AUTHORIZATION_HEADER_KEY) ?: return null
        val user = Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
            .body.subject ?: return null
        return UsernamePasswordAuthenticationToken(user, null, emptyList())

    }
}