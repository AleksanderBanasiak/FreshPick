package com.freshPick.FreshPick.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class RegistrationRequest(

    @field:NotEmpty(message = "Name is mandatory")
    @field:NotBlank(message = "Name is mandatory")
    val name: String,

    @field:Email(message = "Not valid email format")
    @field:NotEmpty(message = "Email is mandatory")
    @field:NotBlank(message = "Email is mandatory")
    val email: String,

    @field:NotEmpty(message = "Password is mandatory")
    @field:NotBlank(message = "Password is mandatory")
    @field:Size(min = 8, message = "Password should be at least 8 letters long")
    val password: String

)
