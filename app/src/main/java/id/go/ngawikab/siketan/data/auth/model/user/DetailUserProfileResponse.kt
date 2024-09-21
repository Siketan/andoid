package id.go.ngawikab.siketan.data.auth.model.user

import id.go.ngawikab.siketan.domain.auth.model.User

data class DetailUserProfileResponse(
    val message: String,
    val userRole: User
)