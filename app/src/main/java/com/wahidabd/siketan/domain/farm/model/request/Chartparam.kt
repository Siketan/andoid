package com.wahidabd.siketan.domain.farm.model.request

import com.wahidabd.siketan.utils.ChartKomuditasQuery
import com.wahidabd.siketan.utils.ChartTypeQuery


/**
 * Created by Wahid on 8/1/2023.
 * Github github.com/wahidabd.
 */


data class Chartparam(
    val jenisPanen: ChartTypeQuery = ChartTypeQuery.TAHUNAN,
    val jenis: ChartKomuditasQuery = ChartKomuditasQuery.FRUIT
)