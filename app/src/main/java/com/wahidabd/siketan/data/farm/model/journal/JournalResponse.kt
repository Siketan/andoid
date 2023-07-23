package com.wahidabd.siketan.data.farm.model.journal

import com.wahidabd.siketan.domain.auth.model.User


/**
 * Created by Wahid on 7/21/2023.
 * Github github.com/wahidabd.
 */


data class JournalResponse(
    val message: String,
    val newData: List<User>
)
