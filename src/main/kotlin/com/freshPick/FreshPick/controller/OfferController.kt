package com.freshPick.FreshPick.controller

import com.freshPick.FreshPick.common.PageResponse
import com.freshPick.FreshPick.dto.OfferRequest
import com.freshPick.FreshPick.dto.OfferResponse
import com.freshPick.FreshPick.service.OfferService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/offer")
class OfferController(
    private val offerService: OfferService
) {


//    TODO: additional options need to be added

    @PostMapping
    fun saveOffer(@RequestBody @Valid request: OfferRequest, authUser: Authentication): ResponseEntity<Long> {
        return ResponseEntity.ok(offerService.save(request, authUser))
    }

    @GetMapping
    fun findAllOffers(
        @RequestParam(name = "page", defaultValue = "0", required = false) page: Int = 0,
        @RequestParam(name = "size", defaultValue = "10", required = false) size: Int = 10,
        authUser: Authentication
    ): ResponseEntity<PageResponse<OfferResponse>>{
        return ResponseEntity.ok(offerService.findAllOffers(page, size, authUser))
    }
}