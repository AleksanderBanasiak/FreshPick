package com.freshPick.FreshPick.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.security.Principal
import java.time.LocalDateTime


@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener::class)
class User (
//    TODO: check if id should not be null
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(unique = true)
    var email: String? = null,

    private var name: String? = null,

    private var password: String? = null,


    @ManyToMany(fetch = FetchType.EAGER)
    var roles: List<Role>? = null,


    @OneToMany(mappedBy = "user",  fetch = FetchType.LAZY)
    var offers: List<Offer>? = null,

//    TODO: acc locked and enabled


    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdDate: LocalDateTime? = null,

    @CreatedDate
    @Column(insertable = false)
    var lastModifiedDate: LocalDateTime? = null

): UserDetails, Principal {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles?.map { SimpleGrantedAuthority(it.name) }?.toMutableList() ?:
        mutableListOf()
    }


    override fun getPassword(): String? {
        return password
    }
    override fun getUsername(): String? {
        return email
    }

    override fun getName(): String? {
        return name
    }

}
