package id.go.ngawikab.siketan.data.farm.model.farm.response


/**
 * Created by Wahid on 6/15/2023.
 * Github github.com/wahidabd.
 */


data class InfoTaniDataResponse<T>(
    val message: String,
    val infotani: List<T>
)
