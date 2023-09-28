package com.example.core.domain

import com.example.core.domain.model.Doa
import kotlinx.coroutines.flow.Flow

class DoaInteractor constructor(private val repository: IDoaRepository) : DoaUseCase {
    override fun getAllDoa(): Flow<Resource<List<Doa>>> = repository.getAllDoa()

    override fun getFavoriteDoa(): Flow<List<Doa>> = repository.getFavoriteDoa()

    override suspend fun setDoaToFavorite(doa: Doa, state: Boolean) =
        repository.setDoaToFavorite(doa, state)
}