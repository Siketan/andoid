package id.go.ngawikab.siketan.data.auth.model

import id.go.ngawikab.siketan.data.auth.model.user.UserResponse


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */

data class AuthDataResponse(
    val message: String? = null,
    val token: String? = null,
    val user: UserResponse? = null
)