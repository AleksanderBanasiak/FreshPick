package com.freshPick.FreshPick.repository

import com.freshPick.FreshPick.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface RoleRepository : JpaRepository<Role, Long> {

    fun findByName(name: String): Optional<Role>
}