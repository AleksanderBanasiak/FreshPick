package com.freshPick.FreshPick.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdDate: LocalDateTime? = null,

    @CreatedDate
    @Column(insertable = false)
    var modifiedDate: LocalDateTime? = null,

    @CreatedBy
    @Column(nullable = false, updatable = false)
    var createdBy: Long? = null,

    @CreatedBy
    @Column(insertable = false)
    var modifiedBy: Long? = null
)
