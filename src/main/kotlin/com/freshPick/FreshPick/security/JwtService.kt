package com.freshPick.FreshPick.security


import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import java.util.function.Function

@Service
class JwtService {

    @Value("\${application.security.jwt.expiration}")
    private val jwtExpiration: Long = 0

    @Value("\${application.security.jwt.secret-key}")
    private lateinit var secretKey: String

    fun extractUsername(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }

    fun <T> extractClaim(token: String, claimsResolver: Function<Claims, T>): T {
        val claims = extractAllClaims(token)
        return claimsResolver.apply(claims)
    }
    fun generateToken(userDetails: UserDetails): String {
        return generateToken(HashMap(), userDetails)
    }

    fun generateToken(extraClaims: Map<String, Any?>, userDetails: UserDetails): String {
        return buildToken(extraClaims, userDetails, jwtExpiration)
    }

    private fun buildToken(extraClaims: Map<String, Any?>, userDetails: UserDetails, expiration: Long): String {
        val authorities = userDetails.authorities
            .map { obj: GrantedAuthority -> obj.authority }
            .toList()
        return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .claim("authorities", authorities)
            .signWith(getSignInKey())
            .compact()
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun getSignInKey(): Key {
        val keyBytes: ByteArray = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}