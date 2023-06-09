package com.wahidabd.siketan.domain.auth.model

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */

data class AuthResponse(
    val message: String? = emptyString(),
    val token: String? = emptyString()
)