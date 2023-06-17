package com.wahidabd.siketan.domain.farm

import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import com.wahidabd.siketan.data.farm.FarmRepository
import com.wahidabd.siketan.data.farm.model.farm.InfoTaniDataResponse
import com.wahidabd.siketan.data.farm.model.store.ProductDataResponse
import com.wahidabd.siketan.domain.farm.model.InfoTani
import com.wahidabd.siketan.domain.farm.model.Product
import com.wahidabd.siketan.domain.farm.model.toDomain
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 6/16/2023.
 * Github github.com/wahidabd.
 */


class FarmInteractor(private val repository: FarmRepository) : FarmUseCase {

    override fun getInfoTani(): Flow<Resource<List<InfoTani>>> =
        object : InternetBoundResource<List<InfoTani>, InfoTaniDataResponse>() {
            override suspend fun createCall(): Flow<Resource<InfoTaniDataResponse>> {
                return repository.getInfoTani()
            }

            override suspend fun saveCallRequest(data: InfoTaniDataResponse): List<InfoTani> {
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

}