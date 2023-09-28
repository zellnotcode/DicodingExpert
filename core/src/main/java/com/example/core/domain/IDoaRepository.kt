package com.example.core.domain

import com.example.core.domain.model.Doa
import kotlinx.coroutines.flow.Flow

interface IDoaRepository {

    fun getAllDoa() : Flow<Resource<List<Doa>>>

    fun getFavoriteDoa() : Flow<List<Doa>>

    suspend fun setDoaToFavorite(doa: Doa, state: Boolean)

}