package com.freshPick.FreshPick.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "role")
@EntityListeners(AuditingEntityListener::class)
data class Role(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(unique = true)
    var name: String,

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    var users: List<User>? = null,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdDate: LocalDateTime? = null,

    @LastModifiedDate
    @Column(insertable = false)
    var lastModifiedDate: LocalDateTime? = null
)
