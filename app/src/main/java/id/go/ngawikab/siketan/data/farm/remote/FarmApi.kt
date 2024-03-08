package id.go.ngawikab.siketan.data.farm.remote

import com.wahidabd.library.data.WebApi
import id.go.ngawikab.siketan.data.farm.model.farm.request.InputTanamanRequest
import id.go.ngawikab.siketan.data.farm.model.farm.response.ChartResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.EventTaniResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.InfoTaniDataResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.InfoTaniResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.InputTanamanResponse
import id.go.ngawikab.siketan.data.farm.model.farm.response.PlantFarmerResponse

import id.go.ngawikab.siketan.data.farm.model.journal.JournalResponse
import id.go.ngawikab.siketan.data.farm.model.store.ProductDataResponse
import id.go.ngawikab.siketan.data.farm.model.store.response.GenericAddResponse
import id.go.ngawikab.siketan.domain.farm.model.response.ChartModel
import id.go.ngawikab.siketan.data.farm.model.farm.response.report.ReportTanamanResponse
import okhttp3.MultipartBody
import retrofit2.Response


/**
 * Created by Wahid on 6/15/2023.
 * Github github.com/wahidabd.
 */


class FarmApi(private val api: FarmApiClient) : WebApi, FarmApiClient {
    override suspend fun getInfoTani(): Response<InfoTaniDataResponse<InfoTaniResponse>> {
        return api.getInfoTani()
    }

    override suspend fun getEvent(): Response<InfoTaniDataResponse<EventTaniResponse>> {
        return api.getEvent()
    }

    override suspend fun getProduct(): Response<ProductDataResponse> {
        return api.getProduct()
    }

    override suspend fun postStore(body: MultipartBody): Response<GenericAddResponse> {
        return api.postStore(body)
    }

    override suspend fun getJournal(): Response<JournalResponse> {
        return api.getJournal()
    }

    override suspend fun addJournal(body: MultipartBody): Response<GenericAddResponse> {
        return api.addJournal(body)
    }

    override suspend fun addPresensi(body: MultipartBody): Response<GenericAddResponse> {
        return api.addPresensi(body)
    }

    override suspend fun getChart(id:Int ,musim: String, jenis: String): Response<ChartResponse> {
        return api.getChart(id,musim, jenis)
    }

    override suspend fun addTanaman(body: InputTanamanRequest): Response<InputTanamanResponse> {
        return api.addTanaman(body)
    }

    override suspend fun getTanaman(id: Int, page:Int,limit:Int): Response<PlantFarmerResponse> {
        return api.getTanaman(id,page,limit)
    }

    override suspend fun addLaporan(body: MultipartBody): Response<GenericAddResponse> {
        return api.addLaporan(body)
    }

    override suspend fun getLaporan(id: Int): Response<ReportTanamanResponse> {
        return api.getLaporan(id)
    }
}