package com.grapefruit.aid.global.security.jwt

import com.grapefruit.aid.global.auth.AuthDetailsService
import com.grapefruit.aid.global.security.exception.ExpiredTokenException
import com.grapefruit.aid.global.security.exception.InvalidTokenException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import java.time.ZonedDateTime
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class TokenProvider(
    private val jwtProperties: JwtProperties,
    private val authDetailsService: AuthDetailsService
) {
    companion object {
        const val ACCESS_TYPE = "access"
        const val REFRESH_TYPE = "refresh"
        const val ACCESS_EXP = 60L * 15
        const val REFRESH_EXP = 60L * 60 * 24 * 7
        const val TOKEN_PREFIX = "Bearer "
    }

    val accessExpiredTime: ZonedDateTime
        get() = ZonedDateTime.now().plusSeconds(ACCESS_EXP)

    fun generateAccessToken(id: String): String =
        generateToken(id, ACCESS_TYPE, jwtProperties.accessSecret, ACCESS_EXP)

    fun generateRefreshToken(id: String): String =
        generateToken(id, REFRESH_TYPE, jwtProperties.refreshSecret, REFRESH_EXP)

    fun resolveToken(req: HttpServletRequest): String? {
        val token = req.getHeader("Authorization") ?: return null
        return parseToken(token)
    }

    fun exactIdFromRefreshToken(refresh: String): String {
        return getTokenSubject(refresh, jwtProperties.refreshSecret)
    }

    fun authentication(token: String): Authentication {
        val userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token, jwtProperties.accessSecret))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun parseToken(token: String): String? =
        if (token.startsWith(TOKEN_PREFIX)) token.replace(TOKEN_PREFIX, "") else null

    fun generateToken(sub: String, type: String, secret: Key, exp: Long): String {
        return Jwts.builder()
            .signWith(secret, SignatureAlgorithm.HS256)
            .setSubject(sub)
            .claim("type", type)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .compact()
    }

    private fun getTokenBody(token: String, secret: Key): Claims {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: ExpiredJwtException) {
            throw ExpiredTokenException()
        } catch (e: Exception) {
            throw InvalidTokenException()
        }
    }

    private fun getTokenSubject(token: String, secret: Key): String =
        getTokenBody(token, secret).subject
}