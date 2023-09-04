package com.wahidabd.siketan.data.farm.remote

import com.wahidabd.library.data.WebApi
import com.wahidabd.siketan.data.farm.model.farm.request.InputTanamanRequest
import com.wahidabd.siketan.data.farm.model.farm.response.EventTaniResponse
import com.wahidabd.siketan.data.farm.model.farm.response.InfoTaniDataResponse
import com.wahidabd.siketan.data.farm.model.farm.response.InfoTaniResponse
import com.wahidabd.siketan.data.farm.model.farm.response.InputTanamanResponse
import com.wahidabd.siketan.data.farm.model.farm.response.TanamanPetaniResponse
import com.wahidabd.siketan.data.farm.model.journal.JournalResponse
import com.wahidabd.siketan.data.farm.model.store.ProductDataResponse
import com.wahidabd.siketan.data.farm.model.store.response.GenericAddResponse
import com.wahidabd.siketan.domain.farm.model.response.ChartModel
import go.ngawikab.siketan.data.farm.model.farm.response.report.ReportTanamanResponse
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

    override suspend fun getChart(jenisPanen: String, jenis: String): Response<ChartModel> {
        return api.getChart(jenisPanen, jenis)
    }

    override suspend fun addTanaman(body: InputTanamanRequest): Response<InputTanamanResponse> {
        return api.addTanaman(body)
    }

    override suspend fun getTanaman(id: Int): Response<TanamanPetaniResponse> {
        return api.getTanaman(id)
    }

    override suspend fun addLaporan(body: MultipartBody): Response<GenericAddResponse> {
        return api.addLaporan(body)
    }

    override suspend fun getLaporan(id: Int): Response<ReportTanamanResponse> {
        return api.getLaporan(id)
    }
}