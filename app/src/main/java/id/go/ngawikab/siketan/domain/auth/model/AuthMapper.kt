package id.go.ngawikab.siketan.domain.auth.model

import id.go.ngawikab.siketan.data.auth.model.AuthDataResponse
import id.go.ngawikab.siketan.data.auth.model.LoginDataRequest
import id.go.ngawikab.siketan.data.auth.model.RegisterDataRequest


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */

fun AuthDataResponse.toDomain(): AuthResponse =
    AuthResponse(message, token, user?.toDomain())

fun AuthResponse.toData(): AuthDataResponse =
    AuthDataResponse(message, token)

fun LoginRequest.toData(): LoginDataRequest =
    LoginDataRequest(nik, password)

fun RegisterRequest.toData(): RegisterDataRequest =
    RegisterDataRequest(nik, no_wa, nama, password)