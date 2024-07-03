package com.freshPick.FreshPick.model

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table


@Entity
@Table(name = "offer")
data class Offer(

    var productName: String? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null

//    TODO: additional options need to be added

): BaseEntity()
