package com.wahidabd.siketan.domain.auth.model


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */

data class RegisterRequest(
    val email: String,
    val no_wa: String,
    val nama: String,
    val password: String,
)