package com.example.core.data.network

import com.example.core.data.network.response.DoaResponseItem
import retrofit2.http.GET

interface ApiService {
    @GET("api")
    suspend fun getAllDoa() : List<DoaResponseItem>
}