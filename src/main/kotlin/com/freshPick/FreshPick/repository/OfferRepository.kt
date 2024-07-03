package com.freshPick.FreshPick.repository

import com.freshPick.FreshPick.model.Offer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface OfferRepository : JpaRepository<Offer, Long>{

}
