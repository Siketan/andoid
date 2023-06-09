package com.wahidabd.siketan.domain.auth.model

import com.wahidabd.siketan.data.auth.model.AuthDataResponse
import com.wahidabd.siketan.data.auth.model.LoginDataRequest
import com.wahidabd.siketan.data.auth.model.RegisterDataRequest


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */

fun AuthDataResponse.toDomain(): AuthResponse =
    AuthResponse(message, token)

fun AuthResponse.toData(): AuthDataResponse =
    AuthDataResponse(message, token)

fun LoginRequest.toData(): LoginDataRequest =
    LoginDataRequest(email, password)

fun RegisterRequest.toData(): RegisterDataRequest =
    RegisterDataRequest(email, no_wa, nama, password)