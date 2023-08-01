package com.wahidabd.siketan.domain.farm.model.response


/**
 * Created by Wahid on 8/1/2023.
 * Github github.com/wahidabd.
 */


data class ChartModel(
    val dataChart: List<ChartDataModel>
)

data class ChartDataModel(
    val label: String,
    val total: String,
    val count: Int,
    val tahunPanen: Int
)