package com.wahidabd.siketan.data.auth.model


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */

data class AuthDataResponse(
    val message: String? = null,
    val token: String? = null,
    val user: UserResponse? = null
)