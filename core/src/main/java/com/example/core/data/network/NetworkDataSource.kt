package com.example.core.data.network

import com.example.core.data.network.response.ApiResponse
import com.example.core.data.network.response.DoaResponseItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NetworkDataSource constructor(private val apiService: ApiService) {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

     fun getAllDoa() : Flow<ApiResponse<List<DoaResponseItem>>> {
        return flow {
            try {
                val response = apiService.getAllDoa()

                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(ioDispatcher)
    }
}