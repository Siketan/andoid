package com.wahidabd.siketan.domain.farm

import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import com.wahidabd.siketan.data.farm.FarmRepository
import com.wahidabd.siketan.data.farm.model.farm.response.EventTaniResponse
import com.wahidabd.siketan.data.farm.model.farm.response.InfoTaniDataResponse
import com.wahidabd.siketan.data.farm.model.farm.response.InfoTaniResponse
import com.wahidabd.siketan.data.farm.model.journal.JournalAddRequest
import com.wahidabd.siketan.data.farm.model.journal.JournalResponse
import com.wahidabd.siketan.data.farm.model.journal.PresensiRequest
import com.wahidabd.siketan.data.farm.model.store.ProductDataResponse
import com.wahidabd.siketan.data.farm.model.store.response.GenericAddResponse
import com.wahidabd.siketan.domain.farm.model.request.ProductParam
import com.wahidabd.siketan.domain.farm.model.response.EventTani
import com.wahidabd.siketan.domain.farm.model.response.InfoTani
import com.wahidabd.siketan.domain.farm.model.response.Product
import com.wahidabd.siketan.domain.farm.model.response.toDomain
import com.wahidabd.siketan.domain.farm.model.toRequest
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 6/16/2023.
 * Github github.com/wahidabd.
 */


class FarmInteractor(private val repository: FarmRepository) : FarmUseCase {

    override fun getInfoTani(): Flow<Resource<List<InfoTani>>> =
        object : InternetBoundResource<List<InfoTani>, InfoTaniDataResponse<InfoTaniResponse>>() {
            override suspend fun createCall(): Flow<Resource<InfoTaniDataResponse<InfoTaniResponse>>> {
                return repository.getInfoTani()
            }

            override suspend fun saveCallRequest(data: InfoTaniDataResponse<InfoTaniResponse>): List<InfoTani> {
                return data.infotani.map {
                    it.toDomain()
                }
            }
        }.asFlow()

    override fun getEvent(): Flow<Resource<List<EventTani>>> =
        object : InternetBoundResource<List<EventTani>, InfoTaniDataResponse<EventTaniResponse>>() {
            override suspend fun createCall(): Flow<Resource<InfoTaniDataResponse<EventTaniResponse>>> {
                return repository.getEvent()
            }

            override suspend fun saveCallRequest(data: InfoTaniDataResponse<EventTaniResponse>): List<EventTani> {
                return data.infotani.map {
                    it.toDomain()
                }
            }

        }.asFlow()


    override fun getProduct(): Flow<Resource<List<Product>>> =
        object : InternetBoundResource<List<Product>, ProductDataResponse>() {
            override suspend fun createCall(): Flow<Resource<ProductDataResponse>> {
                return repository.getProduct()
            }

            override suspend fun saveCallRequest(data: ProductDataResponse): List<Product> {
                return data.productPetani.map {
                    it.toDomain()
                }
            }

        }.asFlow()

    override fun addProduct(data: ProductParam): Flow<Resource<GenericAddResponse>> {
        return object : InternetBoundResource<GenericAddResponse, GenericAddResponse>(){
            override suspend fun createCall(): Flow<Resource<GenericAddResponse>> {
                return repository.addProduct(data.toRequest())
            }

            override suspend fun saveCallRequest(data: GenericAddResponse): GenericAddResponse {
                return data
            }

        }.asFlow()
    }

    override fun getJournal(): Flow<Resource<JournalResponse>> {
        return object : InternetBoundResource<JournalResponse, JournalResponse>(){
            override suspend fun createCall(): Flow<Resource<JournalResponse>> {
                return repository.getJournal()
            }

            override suspend fun saveCallRequest(data: JournalResponse): JournalResponse {
                return data
            }
        }.asFlow()
    }

    override fun addJournal(data: JournalAddRequest): Flow<Resource<GenericAddResponse>> {
        return object : InternetBoundResource<GenericAddResponse, GenericAddResponse>() {
            override suspend fun createCall(): Flow<Resource<GenericAddResponse>> {
                return repository.addJournal(data)
            }

            override suspend fun saveCallRequest(data: GenericAddResponse): GenericAddResponse {
                return data
            }
        }.asFlow()
    }

    override fun addPresensi(data: PresensiRequest): Flow<Resource<GenericAddResponse>> {
        return object : InternetBoundResource<GenericAddResponse, GenericAddResponse>(){
            override suspend fun createCall(): Flow<Resource<GenericAddResponse>> {
                return repository.addPresensi(data)
            }

            override suspend fun saveCallRequest(data: GenericAddResponse): GenericAddResponse {
                return data
            }

        }.asFlow()
    }

}