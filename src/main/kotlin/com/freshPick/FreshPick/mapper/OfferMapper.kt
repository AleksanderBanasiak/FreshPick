package com.freshPick.FreshPick.mapper

import com.freshPick.FreshPick.dto.OfferRequest
import com.freshPick.FreshPick.model.Offer
import com.freshPick.FreshPick.model.User
import org.springframework.stereotype.Service


@Service
class OfferMapper {


    fun mapOfferRequestToOffer(request: OfferRequest, user: User): Offer{
        return Offer(
            productName = request.productName,
            user = user
        )
    }

}