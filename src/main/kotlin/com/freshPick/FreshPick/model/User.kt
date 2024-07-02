package com.freshPick.FreshPick.model

import java.util.UUID

data class User(
    val id: UUID,
    val email: String,
    val password: String,
    val role: Role
)
