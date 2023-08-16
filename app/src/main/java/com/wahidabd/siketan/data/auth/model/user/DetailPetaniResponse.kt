package com.wahidabd.siketan.data.auth.model.user

import com.wahidabd.siketan.domain.auth.model.User


/**
 * Created by Wahid on 8/8/2023.
 * Github github.com/wahidabd.
 */


data class DetailPetaniResponse(
    val message: String,
    val detailTani: User
)