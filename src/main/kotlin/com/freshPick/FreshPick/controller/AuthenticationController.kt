package com.freshPick.FreshPick.controller

import com.freshPick.FreshPick.dto.AuthenticationRequest
import com.freshPick.FreshPick.dto.AuthenticationResponse
import com.freshPick.FreshPick.dto.RegistrationRequest
import com.freshPick.FreshPick.service.AuthenticationService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthenticationController(private val authenticationService: AuthenticationService) {



    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun register(@RequestBody @Valid request: RegistrationRequest): ResponseEntity<Any> {
        authenticationService.register(request)
        return ResponseEntity.accepted().build()
    }

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody @Valid request: AuthenticationRequest): ResponseEntity<AuthenticationResponse> {
        return ResponseEntity.ok(authenticationService.authenticate(request))
    }
}