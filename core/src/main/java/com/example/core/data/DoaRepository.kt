package com.example.core.data

import com.example.core.data.local.LocalDataSource
import com.example.core.data.network.NetworkDataSource
import com.example.core.data.network.response.ApiResponse
import com.example.core.data.network.response.DoaResponseItem
import com.example.core.domain.IDoaRepository
import com.example.core.domain.Resource
import com.example.core.domain.model.Doa
import com.example.core.utils.DoaDataMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DoaRepository constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource,
) : IDoaRepository {
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override fun getAllDoa(): Flow<Resource<List<Doa>>> =
        object : NetworkBoundResource<List<Doa>, List<DoaResponseItem>>() {
            override fun loadFromDB(): Flow<List<Doa>> {
                return localDataSource.getAllDoa().map {
                    DoaDataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<DoaResponseItem>>> {
                return networkDataSource.getAllDoa()
            }


            override suspend fun saveCallResult(data: List<DoaResponseItem>) {
                val doaList = DoaDataMapper.mapResponseToEntities(data)
                withContext(ioDispatcher) {
                    localDataSource.insertFavorite(doaList)
                }
            }

            override fun shouldFetch(data: List<Doa>?): Boolean = true

        }.asFlow()

    override fun getFavoriteDoa(): Flow<List<Doa>> {
        return localDataSource.getFavoriteDoa().map {
            DoaDataMapper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun setDoaToFavorite(doa: Doa, state: Boolean) {
        val doaEntity = DoaDataMapper.mapDomainToEntity(doa)
        withContext(ioDispatcher) {
            localDataSource.setFavoriteDoa(doaEntity, state)
        }
    }
}