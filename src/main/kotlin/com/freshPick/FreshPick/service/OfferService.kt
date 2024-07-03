package com.freshPick.FreshPick.service

import com.freshPick.FreshPick.common.PageResponse
import com.freshPick.FreshPick.dto.OfferRequest
import com.freshPick.FreshPick.dto.OfferResponse
import com.freshPick.FreshPick.mapper.OfferMapper
import com.freshPick.FreshPick.model.User
import com.freshPick.FreshPick.repository.OfferRepository
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service


@Service
class OfferService(
    private val offerRepository: OfferRepository,
    private val offerMapper: OfferMapper

) {
    fun findAllOffers(page: Int, size: Int, authUser: Authentication): PageResponse<OfferResponse>? {
        val user = authUser.principal as User

        println(user.email)

        return null
    }

    fun save(request: OfferRequest, authUser: Authentication): Long? {
        val user = authUser.principal as User
        val offer = offerMapper.mapOfferRequestToOffer(request, user)
        val s = null
        return offerRepository.save(offer).id
    }
}