package id.go.ngawikab.siketan.domain.farm.model.request

import id.go.ngawikab.siketan.utils.ChartKomuditasQuery
import id.go.ngawikab.siketan.utils.ChartTypeQuery


/**
 * Created by Wahid on 8/1/2023.
 * Github github.com/wahidabd.
 */


data class Chartparam(
    val id: Int? = 0,
    val musim: ChartTypeQuery = ChartTypeQuery.EMPTY,
    val jenis: ChartKomuditasQuery = ChartKomuditasQuery.EMPTY
)