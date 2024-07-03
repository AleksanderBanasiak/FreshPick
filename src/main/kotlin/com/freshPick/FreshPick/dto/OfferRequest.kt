package com.freshPick.FreshPick.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class OfferRequest(

    @field:NotEmpty(message = "Product name is mandatory")
    @field:NotBlank(message = "Product name is mandatory")
    val productName: String,

//    TODO: additional options need to be added
)
