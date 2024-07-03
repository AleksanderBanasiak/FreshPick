package com.freshPick.FreshPick.repository

import com.freshPick.FreshPick.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {


    fun findByEmail(email: String): Optional<User>
}