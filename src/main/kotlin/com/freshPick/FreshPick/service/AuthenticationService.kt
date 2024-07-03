package com.freshPick.FreshPick.service

import com.freshPick.FreshPick.dto.AuthenticationRequest
import com.freshPick.FreshPick.dto.AuthenticationResponse
import com.freshPick.FreshPick.dto.RegistrationRequest
import com.freshPick.FreshPick.model.User
import com.freshPick.FreshPick.repository.RoleRepository
import com.freshPick.FreshPick.repository.UserRepository
import com.freshPick.FreshPick.security.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class AuthenticationService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService
) {
    fun register(request: RegistrationRequest) {
        val role = roleRepository.findByName("USER").orElseThrow{ IllegalArgumentException("Role not found")}
// TODO: check if is correct idea to specify id here
        val user = User(
            id = 0,
            email = request.email,
            name = request.name,
            password = passwordEncoder.encode(request.password),
            roles = listOf(role)
        )
        userRepository.save(user)
    }

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse{

        val auth = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password
            )
        )

        val claims = HashMap<String, Any?>()
        val user = auth.principal as User
        claims["name"] = user.name
        val jwtToken = jwtService.generateToken(claims, user)
        val authResponse = AuthenticationResponse()
        authResponse.token = jwtToken
        return authResponse
    }

}