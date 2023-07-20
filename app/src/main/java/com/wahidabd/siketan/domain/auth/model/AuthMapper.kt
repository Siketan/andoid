package com.wahidabd.siketan.domain.auth.model

import com.wahidabd.siketan.data.auth.model.AuthDataResponse
import com.wahidabd.siketan.data.auth.model.LoginDataRequest
import com.wahidabd.siketan.data.auth.model.RegisterDataRequest


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